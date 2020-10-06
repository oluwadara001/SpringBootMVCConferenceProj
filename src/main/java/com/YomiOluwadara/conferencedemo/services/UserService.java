

/**
 * @author OO046152 : Yomi Oluwadara
 * This class contains the CRUD and logic credential functions as it relates to users.
 */

package com.YomiOluwadara.conferencedemo.services;

import com.YomiOluwadara.conferencedemo.dao.UserDao;
import com.YomiOluwadara.conferencedemo.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;

	/**
	 * @return a list of all registered users
	 */
	public List<User> allUsers() {
		return userDao.findAll();
	}

	/**
	 * @param id user id to be returned
	 * @return a user based on their
	 */
	public User findOneUser(@PathVariable Long id) {
		return userDao.getOne(id);
	}

	/**
	 * @param user user to be added to the database
	 * @return newly added user
	 */
	public User addNewUser(@RequestBody final User user) {
		return userDao.saveAndFlush(user);
	}

	/**
	 * @param id id of user to be deleted
	 */
	public void deleteOneUser(@PathVariable Long id) {
		userDao.deleteById(id);
	}

	/**
	 * @param id   of user to be updated
	 * @param user object to be updated
	 * @return updated user
	 */
	public User updateUserInfo(@PathVariable Long id, @RequestBody User user) {
		User existingUser = userDao.getOne(id);
		BeanUtils.copyProperties(user, existingUser, "userid");
		return userDao.saveAndFlush(existingUser);
	}

	/**
	 * @param userName supplied by the  user
	 * @param password supplied by the user
	 * @return
	 */
	public String userLogin(@PathVariable String userName, @PathVariable String password) {
		//TODO://add logic that takes user i/p, validate it, checks against db, then if match
		return "Login successful ";
	}


}