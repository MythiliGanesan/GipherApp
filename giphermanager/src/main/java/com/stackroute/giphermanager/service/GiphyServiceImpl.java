package com.stackroute.giphermanager.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.giphermanager.domain.Giphy;
import com.stackroute.giphermanager.exception.GiphyAlreadyExistsException;
import com.stackroute.giphermanager.exception.GiphyNotFoundException;
import com.stackroute.giphermanager.repository.GiphyRepository;

@Service
public class GiphyServiceImpl implements GiphyService {
	
	
	private final GiphyRepository giphyRepo;
	
	@Autowired
	public GiphyServiceImpl(GiphyRepository giphyRepo) {
		super();
		this.giphyRepo = giphyRepo;
	}
	
	@Override
	public boolean saveGifs(Giphy giphy) throws GiphyAlreadyExistsException {
		final Optional<Giphy> object =  giphyRepo.findById(getIdwithUserIdAndGifId(giphy.getUserId(),giphy.getGifId()));
		System.out.println("The values are : "+object);
		if (object.isPresent()) {
			throw new GiphyAlreadyExistsException("Could not save gif , Gif already exists!");
		}
		giphyRepo.save(giphy);
		return true;
	}

	@Override
	public boolean deleteGifById(String userId,String gifId) throws GiphyNotFoundException {
		Integer id=getIdwithUserIdAndGifId(userId,gifId);
		System.out.println("Id is ... >>>> "+id);
		final Giphy giphy = giphyRepo.findById(id).orElse(null);
		if (giphy == null) {
			throw new GiphyNotFoundException("Could not delete gif, Gif not found!");
		}
		giphyRepo.delete(giphy);
		return true;
	}

	@Override
	public Giphy getGifById(String userId,String gifId) throws GiphyNotFoundException {
		final Giphy giphy = giphyRepo.findById(getIdwithUserIdAndGifId(userId,gifId)).get();
		if (giphy == null) {
			throw new GiphyNotFoundException("Gif not found!");
		}
		return giphy;
	}
	

	@Override
	public Integer getIdwithUserIdAndGifId(String userId, String gifId) {
		List<Giphy> gifsInThisUserId = getMyGifs(userId);
		int requiredId = 0;
		for (Giphy giphy:gifsInThisUserId) {
			System.out.println("Gif id from ui "+ gifId+"...."+"Gif id from db "+ giphy.getGifId()+"....");
			if(gifId.equals(giphy.getGifId())) {
				System.out.println("If Gif id from ui "+ gifId+"...."+"Gifs id from db "+ giphy.getGifId()+"....");
				requiredId = giphy.getId();
			}
		};
		return requiredId;
	}
	
	@Override
	public List<Giphy> getMyGifs(String userId) {
		return giphyRepo.findByUserId(userId);
	}

}
