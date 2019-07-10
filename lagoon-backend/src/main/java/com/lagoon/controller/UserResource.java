package com.lagoon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.lagoon.model.User;
import com.lagoon.service.UserService;

@RestController
@RequestMapping("/api/auth")
public class UserResource {

	@Autowired
	private UserService userService;

	@RequestMapping("/user/getAllUsers")
	public ResponseEntity<Page<User>> findAllUsers(Pageable pageable) {
		return new ResponseEntity<Page<User>>(
				userService.findAllUsers(pageable), HttpStatus.OK);
	}

	@RequestMapping(value = "/user/userName/{userName}", method = RequestMethod.GET)
	public ResponseEntity<User> findByUserName(@PathVariable("userName") String userName) {		
		return new ResponseEntity<User>(
				userService.findByUserName(userName), HttpStatus.OK);
	}

	@RequestMapping(value = "/user/update", method = RequestMethod.POST)
	public ResponseEntity<User> updateUser(@RequestBody User user) {
		userService.save(user);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
}