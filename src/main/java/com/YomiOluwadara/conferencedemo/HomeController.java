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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HomeController {

    HomeService homeService;

    /**
     * @param homeService variable of type HomeService
     */
    public HomeController(HomeService homeService) {
        this.homeService = homeService;
    }


    /**
     * @return returns the application welcome message
     */
    @GetMapping
    @RequestMapping("/home")
    public @ResponseBody
    String welcomeMessage() {
        return homeService.welcomeMessage();// + homeService.getUserType();
    }

    /**
     * @return
     */
    @GetMapping
    @RequestMapping("/home/usertype")
    public @ResponseBody
    String getUserTypeFromHomePage() {
        return homeService.getUserType();
    }

    /**
     * @return the applications' app version stored in applications.properties
     */
    @GetMapping
    @RequestMapping("/")
    public @ResponseBody
    String getAppVersion() {
        return homeService.appVersion();
    }


    @Controller
    public class TymeLeafController {
        /**
         * @param model
         * @return
         */
        @GetMapping("/home")
        public String home(Model model) {
            model.addAttribute("message", homeService.welcomeMessage());
            return "index";
        }
    }
}
