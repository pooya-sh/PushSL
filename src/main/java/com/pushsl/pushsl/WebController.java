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
    public List<Trip> searchTrip(@RequestParam String originName,
                                 @RequestParam String destName,
                                 @RequestParam String date,
                                 @RequestParam String time) {

        String originId = apiData.getSiteInfo(originName).get(0).SiteId;
        String destId = apiData.getSiteInfo(destName).get(0).SiteId;

        return apiData.tripInfo(originId, destId, date, time);
    }

}
