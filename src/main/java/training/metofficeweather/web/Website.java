package training.metofficeweather.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import training.metofficeweather.Location;
import training.metofficeweather.MetAPIReader;

import java.util.List;

@Controller
@EnableAutoConfiguration
public class Website {

    @RequestMapping("/")
    ModelAndView locationInfo() {
        LocationInfo locationInfo = new LocationInfo();
        return new ModelAndView("index", "locationInfo", locationInfo) ;
    }


    @RequestMapping("/weatherInfo")
    ModelAndView weatherInfo(@RequestParam("location") String location) {
        WeatherInfo weatherInfo = new WeatherInfo(location);
        weatherInfo.populateData();
        return new ModelAndView("info", "weatherInfo", weatherInfo) ;
    }


    public static void main(String[] args) throws Exception {
        SpringApplication.run(Website.class, args);
    }

}