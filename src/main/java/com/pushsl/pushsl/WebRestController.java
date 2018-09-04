package com.pushsl.pushsl;

import com.pushsl.pushsl.Objects.SiteInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class WebRestController {

    @Autowired
    APIData apiData;

    @PostMapping("/siteinfo")
    public List<SiteInfo> getSiteInfo(@RequestBody String searchString) {
        System.out.println(searchString);
        return apiData.getSiteInfo(searchString);
    }

}
