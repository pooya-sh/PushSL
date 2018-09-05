package com.pushsl.pushsl;

import com.pushsl.pushsl.Objects.Trip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class WebController {

    @Autowired
    APIData apiData;

//    @ResponseBody
//    @PostMapping("/search")
//    public List<Trip> searchTrip(@RequestParam String originName,
//                                 @RequestParam String destName,
//                                 @RequestParam String date,
//                                 @RequestParam String time) {
//
//        System.out.println("o: "+ originName);
//        System.out.println("d: "+ destName);
//        System.out.println("date: "+ date);
//        System.out.println("time: "+ time);
//
//        String originId = apiData.getSiteInfo(originName).get(0).SiteId;
//        String destId = apiData.getSiteInfo(destName).get(0).SiteId;
//        System.out.println(originId);
//        System.out.println(destId);
//        return apiData.tripInfo(originId, destId, date, time);
//    }

}
