package com.pushsl.pushsl;

import com.pushsl.pushsl.Objects.RealTime;
import com.pushsl.pushsl.Objects.SiteInfo;
import com.pushsl.pushsl.Objects.Trip;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PushslApplicationTests {

    @Autowired
    APIData apiData;

    @Autowired
    DataSource dataSource;

    @Autowired
    Repository repository;

    @Test
    public void contextLoads() {
    }

    @Test
    public void testSiteInfoAPI() {
        List<SiteInfo> list = apiData.getSiteInfo("norgegatan");
        list.forEach(System.out::println);
    }

    @Test
    public void testRealTimeAPI() {
        List<RealTime> list = apiData.getRealTimeInfo("9192", "2");
        list.forEach(System.out::println);
    }

    @Test
    public void testPlannerAPI() {
        List<Trip> list = apiData.tripInfo("9192", "9669", "2018-09-08", "17:30");
        list.forEach(System.out::println);
    }


    @Test
    public void testSQLConnection() {
        String urlkey = System.getenv("url");
        try {
            System.out.println("Trying to connect");
            Connection connection = DriverManager.getConnection(urlkey);
            System.out.println("Connection");

        } catch (Exception e) {
            System.out.println("Unable to make connection with DB");
            e.printStackTrace();
        }
    }

    @Test
    public void testGetSQLData() {
        try {
            var list = repository.listDBdata();
            for (var l : list) {
                System.out.println(l);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDeleteData() {
        try {
            repository.deleteData("1");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testAddData() {
        try {
            repository.addData("nyTest@mail.com", "8764", "15", "123");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}