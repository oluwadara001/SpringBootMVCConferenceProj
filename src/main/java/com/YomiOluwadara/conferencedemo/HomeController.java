/**
 * @author OO046152 :Yomi Oluwadara
 * <p>
 * This is eventually serve as the landing page for the application.
 * @Value : Injects the hard-coded value of the yomi.app.version from the
 * application properties to the variable to which its annotated
 * <p>
 * Method getStatus() uses a map to put the coded version of application
 * from the application.properties file
 * <p>
 * HEROKU link: https://yomi-conferenc-eapp.herokuapp.com/
 * home url :http://localhost:5000/home
 * http://localhost:8080/home
 * version :http://localhost:5000/
 * version http://localhost:8080/ : Note- copy the port number in use in the application.properties file.
 */
package com.YomiOluwadara.conferencedemo;

import com.YomiOluwadara.conferencedemo.controller.HomeService;
import com.YomiOluwadara.conferencedemo.model.Session;
import com.YomiOluwadara.conferencedemo.model.Speaker;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class HomeController {
    //Declare an instance variable of type HomeService, so it can be used to access the methods in the HomeService class
    private final HomeService homeService;
    
    @Value("${yomi.app.version}")
    private String appVersion;

    /**
     * Constructor- implements dependency injection through constructor injection
     * @param homeService variable of type HomeService
     */
    public HomeController(HomeService homeService) {
        this.homeService = homeService;
    }

    /**
     * This method renders the home/landing page.
     * @param model Spring Model to add attributes
     * @return The index view name
     */
    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("message", homeService.welcomeMessage());
        model.addAttribute("appVersion", appVersion);
        return "index";
    }
    
    /**
     * API endpoint to return the application welcome message
     * @return The welcome message as a String
     */
    @GetMapping("/api/welcome")
    @ResponseBody
    public String welcomeMessageApi() {
        return homeService.welcomeMessage();
    }

    /**
     * API endpoint to return the user type
     * @return The user type as a String
     */
    @GetMapping("/api/usertype")
    @ResponseBody
    public String getUserTypeApi() {
        return homeService.getUserType();
    }

    /**
     * API endpoint to return the application version
     * @return The app version as a String
     */
    @GetMapping("/api/version")
    @ResponseBody
    public String getAppVersionApi() {
        return homeService.appVersion();
    }
}
