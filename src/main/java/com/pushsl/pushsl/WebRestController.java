package com.pushsl.pushsl;

import com.pushsl.pushsl.Objects.SiteInfo;
import com.pushsl.pushsl.Objects.Trip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
}
