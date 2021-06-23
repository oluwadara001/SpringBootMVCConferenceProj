package com.YomiOluwadara.conferencedemo;

import com.YomiOluwadara.conferencedemo.model.User;
import com.YomiOluwadara.conferencedemo.controller.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

	UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	/**
	 * @return a list of all users
	 */
	@GetMapping
	public @ResponseBody
	List<User> listAllUsers() {
		return userService.allUsers();
	}

	/**
	 * @param id needed to find the user
	 * @return user
	 */
	@GetMapping
	@RequestMapping("/{id}")
	public User findOneUser(@PathVariable Long id) {
		return userService.findOneUser(id);
	}

	/**
	 * @param user object to be created
	 * @return new user that was created
	 */
	@PostMapping
	public User createNewUser(@RequestBody final User user) {
		return userService.addNewUser(user);
	}

	/**
	 * @param id of user to be deleted
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public void deleteAUser(@PathVariable Long id) {
		userService.deleteOneUser(id);
	}

	/**
	 * @param id   user id to be updated
	 * @param user to be updated
	 * @return updated user object
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.PUT)
	public @ResponseBody
	User updateUser(@PathVariable Long id, @RequestBody User user) {
		return userService.updateUserInfo(id, user);

	}

	/*
	 LEARN ABOUT THE RIGHT ANNOTATION THEN TRY AGAIN
	@GetMapping
	@RequestMapping(value = "{inputPassword}")
	public void getUserLoginInfo(@PathVariable Long inputUserId, @PathVariable String inputPassword){
		userService.getUserLoginCred(inputUserId,inputPassword);
	}

*/


}
