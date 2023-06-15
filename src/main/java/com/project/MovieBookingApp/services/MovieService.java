package com.project.MovieBookingApp.services;

import java.util.List;

import com.project.MovieBookingApp.exception.MovieNameAlreadyExistsExceptions;
import com.project.MovieBookingApp.model.Movie;


public interface MovieService {
	public List<Movie> getAllMovies();
	
	public Movie addMovie(Movie movie) throws MovieNameAlreadyExistsExceptions;
	
	public boolean updateMovie(Movie movie);
	
	public boolean deleteMovie(int mid);
	
	public Movie getMovieById(int mid);
	
	public Movie getMovieByName(String mname);
	
	public Movie getMovieByMnameAndTname(String mname, String Tname);
	
	
}
