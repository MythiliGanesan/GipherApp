package com.stackroute.giphermanager.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.stackroute.giphermanager.domain.Giphy;
import com.stackroute.giphermanager.exception.GiphyAlreadyExistsException;
import com.stackroute.giphermanager.repository.GiphyRepository;
import com.stackroute.giphermanager.service.GiphyServiceImpl;

public class GiphyServiceTest {

	@Mock
	private GiphyRepository giphyRepo;
	@InjectMocks
	private GiphyServiceImpl giphyServiceImpl;
	private Giphy gif;
	Optional<Giphy> options;
	
	@Before
	public void setupMock() {
		MockitoAnnotations.initMocks(this);
		gif = new Giphy(1,"101","https://giphy.com/gifs/zLXBAnyOqmTHa","Sample Gif","G","mythili","https://media3.giphy.com/media/Q1D2YJnWHX20w/giphy-downsized.gif");
		options = Optional.of(gif);
	}
	
	@Test
	public void testMockCreation() {
		assertNotNull(gif);
		assertNotNull("jpaRepository creation fails: use @injectMocks on giphyServiceImpl", giphyRepo);
	}
	
	@Test
	public void testSaveGiphySuccess() throws Exception {
		when(giphyRepo.save(gif)).thenReturn(gif);
		boolean flag = giphyServiceImpl.saveGifs(gif);
		assertEquals("saving movie failed,the call to giphyDAOImpl is returning false,check this method", true,flag);
		verify(giphyRepo, times(1)).save(gif);
		verify(giphyRepo, times(1)).findById(Mockito.anyInt());
	}
	
	@SuppressWarnings("unused")
	@Test
	public void testSaveGiphyFailure() throws GiphyAlreadyExistsException {
		when(giphyRepo.findById(1)).thenReturn(options);
		when(giphyRepo.save(gif)).thenReturn(gif);
		final boolean flag = giphyServiceImpl.saveGifs(gif);
	}
	
	@Test
	public void testDeleteMovieById() throws Exception {
		when(giphyRepo.findById(Mockito.anyInt())).thenReturn(options);
		doNothing().when(giphyRepo).delete(gif);
		boolean flag = giphyServiceImpl.deleteGifById("mythili","101");
		assertEquals("deleting gif failed", true, flag);
		verify(giphyRepo, times(1)).delete(gif);
	}
	
	@Test
	public void testGetMovieById() throws Exception {
		when(giphyRepo.findById(Mockito.anyInt())).thenReturn(options);
		Giphy gif1 = giphyServiceImpl.getGifById("mythili","101");
		assertEquals( gif1, gif);
		verify(giphyRepo, times(1)).findById(Mockito.anyInt());
	}
	
	@Test
	public void testGetMyMovies() throws Exception {
		List<Giphy> gifList = new ArrayList<Giphy>(1);
		gifList.add(gif);
		when(giphyRepo.findByUserId("mythili")).thenReturn(gifList);
		List<Giphy> gif1 = giphyServiceImpl.getMyGifs("mythili");
		assertEquals(gifList, gif1);
		verify(giphyRepo, times(1)).findByUserId("mythili");
	}

}
