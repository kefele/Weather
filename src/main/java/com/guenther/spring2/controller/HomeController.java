package com.guenther.spring2.controller;

import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@Controller
public class HomeController {

    @RequestMapping("/")
    public ModelAndView helloWorld()
    {
        ModelAndView mv = new
                ModelAndView("welcome");
        mv.addObject("message","Hello World!!!");
        mv.addObject("bob", "This is another string");
        mv.addObject("title", "Welcome Page");
        return mv;
    }

    @RequestMapping("/userform")
    public ModelAndView userform () {
        return new ModelAndView("form", "inst",
                "Please enter info: ");
    }

    @RequestMapping("/formhandler")
    public ModelAndView formhandler (
            @RequestParam("fName") String fName,
            @RequestParam("lName") String lName,
            @RequestParam("email") String email,
            @RequestParam("phone") String phone,
            @RequestParam("password") String password

    )

    //server side validation

    {

        //here's where the magic happens

        ModelAndView mv = new ModelAndView("formresponse");
        mv.addObject("fName", fName);
        mv.addObject("lName", lName);
        mv.addObject("email", email);
        mv.addObject("phone", phone);
        mv.addObject("password", password);
        return mv;
    }

    @RequestMapping("/weather")
    public ModelAndView weather () {
        try {
            //this httpClient will make request from other server
            HttpClient http = HttpClientBuilder.create().build();

            //HttpHost holds connection info
            HttpHost host = new HttpHost("forecast.weather.gov", 80, "http");

            //HttpGet will actually retrieve infomation from the specific URI
            HttpGet getPage = new HttpGet("/MapClick.php?lat=42.331427&lon=-83.045754&FcstType=json");

            //actually run it and pull
            HttpResponse resp = http.execute(host,getPage);

            //get the actual content from inside the response
            String jsonString = EntityUtils.toString(resp.getEntity());

            //turn it unto java actual JSON object
            JSONObject json = new JSONObject(jsonString);

            //get response code and some info from JSON
            int status = resp.getStatusLine().getStatusCode();
            String prodCenter = json.get("productionCenter").toString();
            JSONArray days = json.getJSONObject("time").getJSONArray("startPeriodName");
            JSONArray temps = json.getJSONObject("data").getJSONArray("temperature");
            JSONArray foreCst = json.getJSONObject("data").getJSONArray("weather");

            //put it into my web page
            ModelAndView mv = new ModelAndView("weather");
            mv.addObject("status", status);
            mv.addObject("prodCenter", prodCenter);
            mv.addObject("day1",days.getString(0));
            mv.addObject("day2",days.getString(1));
            mv.addObject("day3",days.getString(2));
            mv.addObject("day4",days.getString(3));
            mv.addObject("day5",days.getString(4));
            mv.addObject("day6",days.getString(5));
            mv.addObject("day7",days.getString(6));
            mv.addObject("day8",days.getString(7));
            mv.addObject("day9",days.getString(8));
            mv.addObject("day10",days.getString(9));

            mv.addObject("temp1",temps.getString(0));
            mv.addObject("temp2",temps.getString(1));
            mv.addObject("temp3",temps.getString(2));
            mv.addObject("temp4",temps.getString(3));
            mv.addObject("temp5",temps.getString(4));
            mv.addObject("temp6",temps.getString(5));
            mv.addObject("temp7",temps.getString(6));
            mv.addObject("temp8",temps.getString(7));
            mv.addObject("temp9",temps.getString(8));
            mv.addObject("temp10",temps.getString(9));

            mv.addObject("forC1",foreCst.getString(0));
            mv.addObject("forC2",foreCst.getString(1));
            mv.addObject("forC3",foreCst.getString(2));
            mv.addObject("forC4",foreCst.getString(3));
            mv.addObject("forC5",foreCst.getString(4));
            mv.addObject("forC6",foreCst.getString(5));
            mv.addObject("forC7",foreCst.getString(6));
            mv.addObject("forC8",foreCst.getString(7));
            mv.addObject("forC9",foreCst.getString(8));
            mv.addObject("forC10",foreCst.getString(9));



            return mv;


        } catch (IOException e) {
            e.printStackTrace();
        }catch (JSONException e){
            e.printStackTrace();
        }
        return null;
    }
}