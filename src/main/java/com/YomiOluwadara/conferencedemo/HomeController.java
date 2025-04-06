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
 * home url :http://localhost:8085/home
 * http://localhost:8085/
 * version :http://localhost:8085/home/api/version
 * Note- port number is configured in the application.properties file.
 */
package com.YomiOluwadara.conferencedemo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.YomiOluwadara.conferencedemo.services.AttendeeService;
import com.YomiOluwadara.conferencedemo.services.HomeService;
import com.YomiOluwadara.conferencedemo.services.SessionService;
import com.YomiOluwadara.conferencedemo.services.SpeakerService;

@Controller
public class HomeController {
    //Declare an instance variable of type HomeService, so it can be used to access the methods in the HomeService class
    private final HomeService homeService;
    private final SessionService sessionService;
    private final SpeakerService speakerService;
    private final AttendeeService attendeeService;
    
    @Value("${yomi.app.version}")
    private String appVersion;

    /**
     * Constructor- implements dependency injection through constructor injection
     * @param homeService variable of type HomeService
     */
    public HomeController(HomeService homeService, 
                        SessionService sessionService,
                        SpeakerService speakerService,
                        AttendeeService attendeeService) {
        this.homeService = homeService;
        this.sessionService = sessionService;
        this.speakerService = speakerService;
        this.attendeeService = attendeeService;
    }

    /**
     * This method renders the home/landing page for both root URL and /home endpoint.
     * @param model Spring Model to add attributes
     * @return The index view name
     */
    @GetMapping({"/", "/home"})
    public String home(Model model) {
        model.addAttribute("message", homeService.welcomeMessage());
        model.addAttribute("appVersion", appVersion);
        model.addAttribute("sessions", sessionService.findAll());
        model.addAttribute("speakers", speakerService.findAll());
        model.addAttribute("attendees", attendeeService.findAll());
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
