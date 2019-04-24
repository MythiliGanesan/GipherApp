package com.stackroute.accountmanager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stackroute.accountmanager.model.User;

public interface UserRepository extends JpaRepository<User, String> {


	User findByUserIdAndPassword(String userId, String password);
}
