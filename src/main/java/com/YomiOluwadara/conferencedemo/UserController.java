package com.YomiOluwadara.conferencedemo;

import com.YomiOluwadara.conferencedemo.model.User;
import com.YomiOluwadara.conferencedemo.services.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

	UserService userService;

	/**
	 * @param userService
	 */
	public UserController(UserService userService){
		this.userService = userService;
	}

	/**
	 * @return  a list of all users
	 */
	@GetMapping
	public @ResponseBody
	List<User> listAllUsers(){
		return  userService.allUsers();
	}
	@GetMapping
	@RequestMapping("/{id}")
	public User findOneUser(@PathVariable Long id){
		return userService.findOneUser(id);
	}


}
