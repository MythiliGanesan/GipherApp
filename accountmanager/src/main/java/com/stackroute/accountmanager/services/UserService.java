package com.stackroute.accountmanager.services;

import com.stackroute.accountmanager.exceptions.UserAlreadyExistsException;
import com.stackroute.accountmanager.exceptions.UserNotFoundException;
import com.stackroute.accountmanager.model.User;

public interface UserService {

	boolean saveUser(User user) throws UserAlreadyExistsException, UserNotFoundException;

	public User findByUserIdAndPassword(String userId, String password) throws UserNotFoundException;


}
