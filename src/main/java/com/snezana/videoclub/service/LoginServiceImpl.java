package com.snezana.videoclub.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.snezana.videoclub.dao.LoginDao;
import com.snezana.videoclub.model.User;

/**
 * @see LoginService
 */
@Service("loginService")
@Transactional
public class LoginServiceImpl implements LoginService{

	@Autowired
	private LoginDao loginDao;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public void save(User user){
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		loginDao.save(user);
	}
	
	public User findById(int id) {
		return loginDao.findById(id);
	}

	public User findByUsername(String username) {
		return loginDao.findByUsername(username);
	}
	
	public void changeUserPassword(User user, String password) {
	    user.setPassword(passwordEncoder.encode(password));
	}
	
	public boolean checkIfValidOldPassword(final User user, final String oldPassword) {
		return passwordEncoder.matches(oldPassword, user.getPassword());
	}

}
