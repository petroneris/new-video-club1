package com.snezana.videoclub.service;

import com.snezana.videoclub.model.User;

/**
 * Service interface for login operations
 */
public interface LoginService {

	/**
	 * Service for saving User data
	 * @param user
	 */
	void save(User user);
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	User findById(int id);
	
	/**
	 * 
	 * @param username
	 * @return
	 */
	User findByUsername(String username);
	
	/**
	 * Service method for changing User password by User
	 * @param user {@link User} object
	 * @param newpassword that should be set
	 */
	void changeUserPassword(User user, String newpassword);
	
	/**
	 * Service method that checks if the old password is valid
	 * @param user
	 * @param oldPassword that should be changed
	 * @return
	 */
	public boolean checkIfValidOldPassword(final User user, final String oldPassword);
	
}