package com.lagoon.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.lagoon.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import com.lagoon.service.UserPrinciple;
import com.lagoon.service.UserService;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	UserService userRepository;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

//		User user = userRepository.findByUserName(username).orElseThrow(
//				() -> new UsernameNotFoundException("User Not Found with -> username or email : " + username));

		User user = userRepository.findByUserName(username);
		return UserPrinciple.build(user);
	}
}