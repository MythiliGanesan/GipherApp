package com.stackroute.giphermanager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stackroute.giphermanager.domain.Giphy;


public interface GiphyRepository extends JpaRepository<Giphy, Integer> {

	List<Giphy> findByUserId(String userId);
	
}