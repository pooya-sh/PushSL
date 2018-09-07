package com.pushsl.pushsl;

import com.pushsl.pushsl.Objects.RealTimeBusesAndMetros;
import com.pushsl.pushsl.Objects.SiteInfo;
import com.pushsl.pushsl.Objects.Trip;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PushslApplicationTests {

    @Autowired
    APIData apiData;

    @Test
    public void contextLoads() {
    }

    @Test
    public void testSiteInfoAPI() {
        List<SiteInfo> list = apiData.getSiteInfo("Gullmarsplan (Stockholm)".replaceAll(" ", "%20"));
        list.forEach(System.out::println);
    }

    @Test
    public void testRealTimeAPI() {
        List<RealTimeBusesAndMetros> list = apiData.getRealTimeInfo("9192", "2");
        list.forEach(System.out::println);
    }

    @Test
    public void testPlannerAPI() {
        List<Trip> list = apiData.tripInfo("9192", "9669", "2018-09-08", "17:30");
        list.forEach(System.out::println);
    }
}
