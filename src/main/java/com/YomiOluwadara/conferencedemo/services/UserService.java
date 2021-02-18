

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

import java.sql.Connection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.*;

import static sun.plugin2.os.windows.OVERLAPPED.size;


@Service
public class UserService {

	private static final int CODE_CACHE_MAX_SIZE = 5000;

	@Autowired
	private UserDao userDao;

	@Autowired
	HomeService homeService;

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

	public UserService(Connection connection) {
		super();
	}

	/**
	 * Method adds an object of CustomerDao to the cache memory if not already existing in cache.
	 * it holds the first 5000 records. Typically, it serves as a temporary database- holding table
	 * <p>
	 * <p>
	 * use wrapper class for long- see https://www.w3schools.com/java/java_wrapper_classes.asp
	 * map.entry : https://www.geeksforgeeks.org/map-entry-interface-java-example/
	 * LinkedHashMap --> https://www.geeksforgeeks.org/linkedhashmap-class-java-examples/
	 */
	private static final HashMap<Long, User> userDaoCache = new LinkedHashMap<Long, User>(5, 0.75f, true);

	protected boolean removeEldestEntry(Map.Entry<Long, UserDao> eldest) {
		return size() > CODE_CACHE_MAX_SIZE;
	}


	/**
	 * @param userId of a user to be used to fetch and add userDao to cache
	 */
	public User addUserDaoToCache(long userId) {
		if (userId > 0 && !userDaoCache.containsKey(userId)) {
			userDaoCache.put(userId, findOneUser(userId));
			return userDaoCache.get(userId);
		} else
			return null;
	}

	/**
	 * This method takes in user login credentials, checks if it has a match in the userDaoCache. if it does, it calls
	 * the login method, otherwise, calls the fail method
	 *
	 * @param inputUserId   supplied by the  user
	 * @param inputPassword supplied by the user
	 * @return
	 */
	public void getUserLoginCred(@PathVariable Long inputUserId, @PathVariable String inputPassword) {
		Long userId = 0l;
		String password = "";

		if (!userDaoCache.isEmpty()) {
			if (userDaoCache.containsKey(inputUserId)) {
				userId = userDaoCache.get(inputUserId).getUserId();
				password = userDaoCache.get(inputPassword).getPassword();
				if (inputUserId == userId && inputPassword.equals(password)) {
					loginSuccessful();
				} else
					loginFailedMessage();
			}
		}
	}


	/**
	 *
	 */
	public void loginSuccessful() {
		//placeholder method
	}

	/**
	 * @return the login fail message.
	 */
	public String loginFailedMessage() {
		return "Login has failed, check  usr credentials and try again";
	}


}