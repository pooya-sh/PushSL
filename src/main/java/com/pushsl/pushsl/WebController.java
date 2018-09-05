package com.pushsl.pushsl;

import com.pushsl.pushsl.Objects.Trip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class WebController {

    @Autowired
    APIData apiData;

    @PostMapping("/search")
    public List<Trip> searchTrip(@RequestParam String originId,
                                 @RequestParam String destId,
                                 @RequestParam String date,
                                 @RequestParam String time) {
        System.out.println("o: " + originId);
        System.out.println("d: " + destId);
        System.out.println("date: " + date);
        System.out.println("time: " + time);
        return apiData.tripInfo(originId, destId, date, time);
    }

}
