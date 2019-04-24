package com.stackroute.giphermanager.controller;

import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.giphermanager.domain.Giphy;
import com.stackroute.giphermanager.exception.GiphyAlreadyExistsException;
import com.stackroute.giphermanager.exception.GiphyNotFoundException;
import com.stackroute.giphermanager.service.GiphyService;

import io.jsonwebtoken.Jwts;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping(path = "/api/v1/giphyService")
public class GiphyController {
	
	
	@Autowired
	private GiphyService giphyService;
	
	@PostMapping("/gif")
	public ResponseEntity<?> saveNewGif(@RequestBody Giphy giphy, HttpServletRequest request,HttpServletResponse response) {
		System.out.println("saving gif");
		String userId = getGifByUserId(request);
		System.out.println("userId::" + userId);
		try {
			giphy.setUserId(userId);
			giphyService.saveGifs(giphy);
		} catch (GiphyAlreadyExistsException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
		}
		return new ResponseEntity<Giphy>(giphy, HttpStatus.CREATED);
	}

	String getGifByUserId(HttpServletRequest request) {
		final String authHeader = request.getHeader("authorization");
		final String token = authHeader.substring(7);
		String userId = Jwts.parser().setSigningKey("secretkey").parseClaimsJws(token).getBody().getSubject();
		return userId;
	}

	@DeleteMapping(value = "/gif/{id}")
	public ResponseEntity<?> deleteGifById(@PathVariable("id") String gifId, HttpServletRequest request) {
		try {
			String userId = getGifByUserId(request);
			@SuppressWarnings("unused")
			Integer id = giphyService.getIdwithUserIdAndGifId(userId, gifId);
			giphyService.deleteGifById(userId, gifId);
		} catch (GiphyNotFoundException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<String>("Gif deleted successfully", HttpStatus.OK);
	}

	@GetMapping(path = "/gif/{id}")
	public ResponseEntity<?> fetchGifById(@PathVariable("id") String gifId, HttpServletRequest request) {
		Giphy thisGif;
		try {
			String userId = getGifByUserId(request);
			@SuppressWarnings("unused")
			Integer id = giphyService.getIdwithUserIdAndGifId(userId, gifId);
			thisGif = giphyService.getGifById(userId, gifId);
		} catch (GiphyNotFoundException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Giphy>(thisGif, HttpStatus.OK);
	}

	@GetMapping("/gifs")
	public @ResponseBody ResponseEntity<List<Giphy>> fetchMyGifs(final ServletRequest req,final ServletResponse res) throws GiphyNotFoundException {
		final HttpServletRequest request = (HttpServletRequest) req;
		String userId = getGifByUserId(request);
		System.out.println("userId::" + userId);
		return new ResponseEntity<List<Giphy>>(giphyService.getMyGifs(userId), HttpStatus.OK);
	}

}
