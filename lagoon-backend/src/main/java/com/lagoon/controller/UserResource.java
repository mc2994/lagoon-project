package com.lagoon.controller;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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

//	@RequestMapping("/user/getAllUsers")
//	public ResponseEntity<Page<User>> findAllUsers(Pageable pageable) {
//		System.out.println(">>>>>>>>>>>>>>>>>>>>> ");
//		return new ResponseEntity<Page<User>>(
//				userService.findAllUsers(pageable), HttpStatus.OK);
//	}
	
	@GetMapping(value="/user/findUser")
	public User findUser() {
		User a = new User();
		a.setUserId(1l);
		a.setCreated(new Date());
		a.setFirstName("Mc Kinley");
		a.setLastName("Tolentino");
		a.setPassword("sdadsaddsa");
		a.setUserName("sdadsaddsa");
		return a;
	}
	
	@RequestMapping("/user/getHello")
	public String getHello() {
		return "hello world";
	}

	@RequestMapping(value = "/user/userName/{userName}", method = RequestMethod.GET)
	public ResponseEntity<?> findByUserName(@PathVariable("userName") String userName) {
		User aa = userService.findByUserName(userName);
		if(aa==null) {
			Map<String, Object> body = new LinkedHashMap<>();
			body.put("userName", "Username not found");
			
			return new ResponseEntity<>(body,HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<User>(
				userService.findByUserName(userName), HttpStatus.OK);
	}

	@RequestMapping(value = "/user/update", method = RequestMethod.POST)
	public ResponseEntity<User> updateUser(@RequestBody User user) {
		userService.save(user);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
}