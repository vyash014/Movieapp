package com.project.MovieBookingApp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import com.project.MovieBookingApp.model.Movie;
import com.project.MovieBookingApp.repository.MovieRepository;


@DataJpaTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
public class MovieRepoTest {
		
	@Autowired
	private MovieRepository movieRepo;
	
	private Movie movie = new Movie();// real object
	
	@BeforeEach
	public void init()
	{
		List<String> movieList = new ArrayList<String>();
		movie.setMovieName("Test Movie");
		System.out.println(movie.getMovieName());
		movieList.add("Guest Movie");
		movieList.add("Admin movie");
	}
	
	@Test
	public void saveMovieSuccess() throws Exception
	{
		Movie movie1 = null;
		System.err.println(movie1.getMovieName());
		//movie1 = movieRepo.findById(movie.getMovieId()).get();
		assertNull(movie1.getMovieName());
	}
	
//	@Test
//	public void saveMovieFailure() throws Exception
//	{
//		Movie movie1 = null;
//		
//		if(movieRepo.findAll().toString().isEmpty())
//		{
//			movieRepo.save(movie);
//		movie1 = movieRepo.findAll().get(0);
//		
//		}
//
//		assertNull(movie1);
//	}
}
