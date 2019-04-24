package com.stackroute.accountmanager.controller;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.stackroute.accountmanager.model.User;
import com.stackroute.accountmanager.services.SecurityTokenGenerator;
import com.stackroute.accountmanager.services.UserService;

@RestController
@EnableWebMvc
@CrossOrigin("http://localhost:4200")
@RequestMapping("/api/v1/userservice")

public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private SecurityTokenGenerator tokenGenerator;

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@RequestBody User user) {
		try {
			userService.saveUser(user);
			return new ResponseEntity<String>("User registered successfully", HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<String>("{ \"message\": \"" + e.getMessage() + "\"}", HttpStatus.CONFLICT);
		}
	}

	@PostMapping("/signin")
	public ResponseEntity<?> loginUser(@RequestBody User loginDetail) {

		try {
	
			String userId = loginDetail.getUserId();
			String password = loginDetail.getPassword();

			if (userId == null || password == null) {
				throw new Exception("Username or password cannot be empty");

			}

			User user = userService.findByUserIdAndPassword(userId, password);
			if (user == null) {
				throw new Exception("User with given Id does not exists");
			}
			String pwd = user.getPassword();
			if (!password.equals(pwd)) {
				throw new Exception("Invalid login credential, Please check username and password ");
			}

			Map<String, String> map = tokenGenerator.generateToken(user);
			return new ResponseEntity<Map<String, String>>(map, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<String>("{ \"message\": \"" + e.getMessage() + "\"}", HttpStatus.UNAUTHORIZED);
		}

	}

}
