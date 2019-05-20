package com.lagoon.controller;

import java.util.Map;
import javax.servlet.ServletException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.lagoon.config.JwtProvider;
import com.lagoon.config.JwtResponse;
import com.lagoon.model.User;
import com.lagoon.service.UserService;

@RestController
@RequestMapping("/api/auth/")
public class UserController {

	@Autowired
	private UserService userService;
	
    @Autowired
    private AuthenticationManager authenticationManager;
 
    @Autowired
    private PasswordEncoder encoder;
 
    @Autowired
    private JwtProvider jwtProvider;

	@RequestMapping(value = "login", method = RequestMethod.POST)
	public ResponseEntity<?>  login(@RequestBody Map<String, String> json) throws ServletException {		
		String userName = json.get("userName");
		String password = json.get("password");

		Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userName,password));
 
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateJwtToken(authentication);
        return ResponseEntity.ok(new JwtResponse(jwt));
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<User> registerUser(@RequestBody User user) {
		user.setPassword(encoder.encode(user.getPassword()));
		userService.save(user);
		return new ResponseEntity<User>(HttpStatus.OK);
	}
}