package com.stackroute.giphermanger.controllers;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.giphermanager.controller.GiphyController;
import com.stackroute.giphermanager.domain.Giphy;
import com.stackroute.giphermanager.service.GiphyService;

@RunWith(SpringRunner.class)

public class GiphyControllerTest {
	
	private MockMvc giphyMockMvc;
	@MockBean
	private GiphyService giphyService;
	@InjectMocks
	private GiphyController giphyController;
	private Giphy giphy;
	static List<Giphy> gifs;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		giphy = new Giphy(1,"101","https://giphy.com/gifs/zLXBAnyOqmTHa","Sample Gif","G","mythili","https://media3.giphy.com/media/Q1D2YJnWHX20w/giphy-downsized.gif");
		giphyMockMvc = MockMvcBuilders.standaloneSetup(giphyController).build();
		gifs = new ArrayList<Giphy>();
		giphy = new Giphy(2,"102","https://giphy.com/gifs/zLXBAnyOqmTHa","Sample Gif1","G","mythili12","https://media3.giphy.com/media/Q1D2YJnWHX20w/giphy-downsized.gif");
		gifs.add(giphy);
		giphy = new Giphy(3,"103","https://giphy.com/gifs/zLXBAnyOqmTHa","Sample Gif2","G","mythili23","https://media3.giphy.com/media/Q1D2YJnWHX20w/giphy-downsized.gif");
		gifs.add(giphy);
	}
	
	@Test
	public void testSaveNewGif() throws Exception {
		when(giphyService.saveGifs(giphy)).thenReturn(true);
		String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIwMSIsImlhdCI6MTU1MTk1NTczMn0.6yaf903fgfpiCQ3K_e-J8RUP48JcJ1jqVIO1xbrkRwo";
		giphyMockMvc.perform(post("/api/v1/giphyService/gif").header("authorization", "Bearer " + token).contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(giphy)))
		           .andExpect(status().isCreated()).andDo(print());
		verify(giphyService, times(1)).saveGifs(Mockito.any(Giphy.class));
	}
	
	@Test
	public void testDeleteGifById() throws Exception {
		String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIwMSIsImlhdCI6MTU1MTk1NTczMn0.6yaf903fgfpiCQ3K_e-J8RUP48JcJ1jqVIO1xbrkRwo";
		when(giphyService.deleteGifById(Mockito.anyString(), Mockito.anyString())).thenReturn(true);
		giphyMockMvc.perform(delete("/api/v1/giphyService/gif/3").header("authorization", "Bearer " + token)
				.contentType(MediaType.APPLICATION_JSON).content(asJsonString(giphy)))
				.andExpect(status().isOk()).andDo(print());
		verify(giphyService, times(1)).deleteGifById(Mockito.anyString(),Mockito.anyString());
	}
		
  @Test
	public void testFetchMyGifs() throws Exception {
		String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIwMSIsImlhdCI6MTU1MTk1NTczMn0.6yaf903fgfpiCQ3K_e-J8RUP48JcJ1jqVIO1xbrkRwo";
		when(giphyService.getMyGifs(Mockito.anyString())).thenReturn(new ArrayList<>());
		giphyMockMvc.perform(get("/api/v1/giphyService/gifs").header("authorization", "Bearer " + token)
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andDo(print());
		verify(giphyService, times(1)).getMyGifs(Mockito.anyString());
	}
	
	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
