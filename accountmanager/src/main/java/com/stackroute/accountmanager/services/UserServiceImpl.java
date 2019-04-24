package com.stackroute.accountmanager.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.accountmanager.exceptions.UserAlreadyExistsException;
import com.stackroute.accountmanager.exceptions.UserNotFoundException;
import com.stackroute.accountmanager.model.User;
import com.stackroute.accountmanager.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	
	@Autowired
	private final UserRepository userRepo;

	public UserServiceImpl(UserRepository userRepo) {
		super();
		this.userRepo = userRepo;
	}

	@Override
	public boolean saveUser(User user) throws UserAlreadyExistsException {

		Optional<User> u1 = userRepo.findById(user.getUserId());
		if (u1.isPresent()) {
			throw new UserAlreadyExistsException("User with Id already exists");
		}
		userRepo.save(user);

		return true;
	}

	@Override
	public User findByUserIdAndPassword(String userId, String password) throws UserNotFoundException {

		User user = userRepo.findByUserIdAndPassword(userId, password);

		if (user == null) {
			throw new UserNotFoundException("UserId and Password mismatch");
		}
		return user;
	}

}
