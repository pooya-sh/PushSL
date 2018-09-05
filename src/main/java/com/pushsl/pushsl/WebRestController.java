package com.pushsl.pushsl;

import com.pushsl.pushsl.Objects.SiteInfo;
import com.pushsl.pushsl.Objects.Trip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class WebRestController {

    @Autowired
    APIData apiData;

    @PostMapping("/siteinfo")
    public List<SiteInfo> getSiteInfo(@RequestBody String searchString) {
        System.out.println(searchString);
        System.out.println(apiData.getSiteInfo(searchString).size());
        return apiData.getSiteInfo(searchString);
    }


    @PostMapping("/search")
    public List<Trip> searchTrip(@RequestParam String originName,
                                 @RequestParam String destName,
                                 @RequestParam String date,
                                 @RequestParam String time) {

        System.out.println("o: "+ originName);
        System.out.println("d: "+ destName);
        System.out.println("date: "+ date);
        System.out.println("time: "+ time);

        String originId = apiData.getSiteInfo(originName).get(0).SiteId;
        String destId = apiData.getSiteInfo(destName).get(0).SiteId;
        System.out.println(originId);
        System.out.println(destId);
        apiData.tripInfo(originId, destId, date, time).forEach(System.out::println);
        return apiData.tripInfo(originId, destId, date, time);
    }
}
