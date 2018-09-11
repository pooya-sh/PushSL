package com.pushsl.pushsl;

import com.pushsl.pushsl.Objects.SiteInfo;
import com.pushsl.pushsl.Objects.Trip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcProperties;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.swing.text.View;
import java.sql.SQLException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
public class WebRestController {

    @Autowired
    APIData apiData;

    @Autowired
    Repository repository;

    @PostMapping("/siteinfo")
    public List<SiteInfo> getSiteInfo(@RequestBody String searchString) {
        return apiData.getSiteInfo(searchString);
    }


    @PostMapping("/search")
    public List<Trip> searchTrip(@RequestParam String originName,
                                 @RequestParam String destName,
                                 @RequestParam String date,
                                 @RequestParam String time) {

        String originId = apiData.getSiteInfo(originName).get(0).SiteId;
        String destId = apiData.getSiteInfo(destName).get(0).SiteId;
        return apiData.tripInfo(originId, destId, date, time);
    }

    @PostMapping("/reminder")
    public boolean addReminder(@RequestBody Trip trip) {
        if(trip != null) {
            try {
                repository.addData(trip.email, trip.legList.get(0).number, trip.reminderMinutes, apiData.getSiteInfo(trip.legList.get(0).Origin.name).get(0).SiteId);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return true;
        }
        else {
            return false;
        }
    }

    @PostMapping("/checktime")
    public String checkRealTime(@RequestBody Trip trip) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime startDateTime = LocalDateTime.parse(trip.startDate + " " + trip.startTime, formatter);
        LocalDateTime currentDateTime = LocalDateTime.now();
        Duration timeBetween = Duration.between(currentDateTime, startDateTime);
        if(timeBetween.getSeconds() > 1800) {
            return trip.startDate + "T" + trip.startTime;
        } else {
            String s = apiData.getRemainingTime(trip);
            System.out.println(s);
            return s;
        }
    }
}