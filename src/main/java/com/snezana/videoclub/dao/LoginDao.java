package com.snezana.videoclub.dao;

import com.snezana.videoclub.model.User;

/**
 * DAO interface for login operations
 */
public interface LoginDao {

	/**
	 * Saves new User to db.
	 * @param user {@link User} object to be inserted into database.           
	 */
	void save(User user);

	/**
	 * Searches for User by its id and finds User from database
	 * @param id (unique) identity number of {@link User}          
	 * @return {@link User} with the given id, or null if does not exist.
	 */
	User findById(int id);

	/**
	 * Searches for User by its username and finds User from database	 
	 * @param username (unique) of {@link User}           
	 * @return {@link User} with the given username, or null if does not exist.
	 */
	User findByUsername(String username);

}
