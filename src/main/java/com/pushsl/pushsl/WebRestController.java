package com.pushsl.pushsl;

import com.pushsl.pushsl.Objects.SiteInfo;
import com.pushsl.pushsl.Objects.Trip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
public class WebRestController {

    @Autowired
    APIData apiData;

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
        if(trip != null)
            return true;
        else
            return false;
    }

    @PostMapping("/checktime")
    public String checkRealTime(@RequestBody Trip trip) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime startDateTime = LocalDateTime.parse(trip.startDate + " " + trip.startTime, formatter);
        LocalDateTime currentDateTime = LocalDateTime.now();
        Duration timeBetween = Duration.between(currentDateTime, startDateTime);
        System.out.println(timeBetween.getSeconds() + " seconds left");
        if(timeBetween.getSeconds() > 1800) {
            System.out.println("more than 30 minutes left, fetching timetable time....");
            return trip.startDate + "T" + trip.startTime;
        } else {
            System.out.println("less than 30 minutes left, fetching real time");
            return apiData.getRemainingTime(trip);
        }
    }
}
