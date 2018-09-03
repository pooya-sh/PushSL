package com.pushsl.pushsl;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.pushsl.pushsl.Objects.SiteInfo;
import org.springframework.boot.jackson.JsonObjectSerializer;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Component
public class APIData {


    public List<SiteInfo> getSiteInfo(String searchString) {
        String format = "json";
        String key = System.getenv("SiteInfoKey");
        boolean stationsonly = true;
        int maxresults = 10;

        String urlString = "http://api.sl.se/api2/typeahead." + format
                + "?key=" + key
                + "&searchstring=" + searchString
                + "&stationsonly=" + stationsonly
                + "&maxresults=" + maxresults;

        String result = "";

        try {
            URL url = new URL(urlString);
            Scanner sc = new Scanner(url.openStream());
            result = sc.nextLine();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Gson gson = new Gson();

        JsonObject jsonObject = new JsonParser().parse(result).getAsJsonObject();
        JsonArray array = jsonObject.get("ResponseData").getAsJsonArray();
        List<SiteInfo> siteInfoList = new ArrayList<>();

        for(int i = 0; i < array.size(); i++) {
            siteInfoList.add(gson.fromJson(array.get(i) , SiteInfo.class));
        }

        return siteInfoList;



    }

}
