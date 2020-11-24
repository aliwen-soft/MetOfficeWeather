package training.metofficeweather.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@EnableAutoConfiguration
public class Website {

    @RequestMapping("/")
    ModelAndView home() {
        return new ModelAndView("index");
    }

    @RequestMapping("/weatherInfo")
    ModelAndView weatherInfo(@RequestParam("locationId") String locationId) {
        WeatherInfo weatherInfo = new WeatherInfo(locationId);
        weatherInfo.populateData();
        return new ModelAndView("info", "weatherInfo", weatherInfo) ;
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Website.class, args);
    }

}