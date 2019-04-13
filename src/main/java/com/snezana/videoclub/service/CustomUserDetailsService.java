package com.snezana.videoclub.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.snezana.videoclub.model.User;
import com.snezana.videoclub.model.Role;

/**
 * Service responsible for providing authentication details to Authentication Manager
 * Implements Spring's {@code org.springframework.security.core.userdetails.UserDetailsService} interface.
 */
@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {
	
	static final Logger LOG = LoggerFactory.getLogger(CustomUserDetailsService.class);

	@Autowired
	private LoginService loginService;
	
	/**
	 * A method that takes username and returns org.springframework.security.core.userdetails.User object.
	 * We populate this object using our own LoginService which gets data from database using LoginDao object
	 */
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = loginService.findByUsername(username);
		LOG.info("User : {}", user);
		if (user == null) {
			LOG.info("User not found");
			throw new UsernameNotFoundException("Username not found");
		}
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), true,
				true, true, true, getGrantedAuthorities(user));
	}

	/**
	 * Returns a list of authorities that are assigned to a user.
	 * @param user the user for which the list is asked for.
	 * @return
	 */
	private List<GrantedAuthority> getGrantedAuthorities(User user) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		for (Role userProfile : user.getRoles()) {
			LOG.info("UserProfile : {}", userProfile);
			authorities.add(new SimpleGrantedAuthority("ROLE_" + userProfile.getName()));
		}
		LOG.info("authorities : {}", authorities);
		return authorities;
	}

}
