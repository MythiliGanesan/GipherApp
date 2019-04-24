package com.stackroute.giphermanager.service;

import java.util.List;

import com.stackroute.giphermanager.domain.Giphy;
import com.stackroute.giphermanager.exception.GiphyAlreadyExistsException;
import com.stackroute.giphermanager.exception.GiphyNotFoundException;

public interface GiphyService {
	
	boolean saveGifs(Giphy giphy) throws GiphyAlreadyExistsException;
	
	boolean deleteGifById(String userId,String gifId) throws GiphyNotFoundException;
	
	Giphy getGifById(String userId,String gifId) throws GiphyNotFoundException;
	
	List<Giphy> getMyGifs(String userId) throws GiphyNotFoundException;

	Integer getIdwithUserIdAndGifId(String userId, String gifId);
	

}
