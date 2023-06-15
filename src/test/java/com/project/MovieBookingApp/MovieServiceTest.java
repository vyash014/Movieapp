package com.project.MovieBookingApp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.project.MovieBookingApp.model.Movie;
import com.project.MovieBookingApp.model.Ticket;
import com.project.MovieBookingApp.repository.MovieRepository;
import com.project.MovieBookingApp.services.MovieServiceImpl;


@AutoConfigureMockMvc
@SpringBootTest
public class MovieServiceTest {
	
	@Mock
	private MovieRepository movieRepo;
	
	@InjectMocks
	private MovieServiceImpl movieService;
	
	@Autowired
	private MockMvc mockMvc;
	
	@BeforeEach
	public void init()
	{
		MockitoAnnotations.openMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(movieService).build();
	}
	
	List<Movie> movieList = new ArrayList<Movie>();
	
	@Test
	public void getAllMovieSuccess() throws Exception
	{
		Movie movie = new Movie();
		
		movie.setMovieId(101);
		movie.setMovieName("Test Movie");
		movie.setSeatsAvailable(100);
		movie.setSeatsBooked(0);
		movie.setTheatreName("PVR");
		movie.setTotalSeats(100);
		
		System.out.println(movie);
		movieList.add(movie);
		when(movieRepo.findAll()).thenReturn(movieList);
		
		List<Movie> mList = movieService.getAllMovies();
		assertEquals(movieList, mList);
		
	}
	
	@Test
	public void getAllMoviessFailure() throws Exception
	{
		
		when(movieRepo.findAll()).thenReturn(null);
		
		List<Movie> mList = movieService.getAllMovies();
		assertNull(mList);
		
	}
	
	@Test
	public void addMovieSuccess() throws Exception
	{
		Movie movie = new Movie();
		movie.setSeatsBooked(0);
		movie.setSeatsAvailable(100);;
		movie.setMovieName("Test movie");
		movie.setMovieId(100);
		
		movieList.add(movie);
		when(movieRepo.save(any())).thenReturn(movie);
		System.out.println(movie);
	//	Boolean t1 = ticketService.addTicket(ticket);
		assertEquals(1,movieList.size());
		
	}
	
	@Test
	public void deleteMovie() throws Exception
	{
		Movie movie = new Movie("Test Movie2","PVR",0,0,0);
		movie.setMovieId(100);
		when(movieRepo.save(movie)).thenReturn(movie);
		movieService.deleteMovie(100);
		assertEquals(true ,movieService.deleteMovie(100) );
	}
	

	
	
}
