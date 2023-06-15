package com.project.MovieBookingApp.services;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.MovieBookingApp.exception.MovieNameAlreadyExistsExceptions;
import com.project.MovieBookingApp.model.Movie;
import com.project.MovieBookingApp.repository.MovieRepository;
import com.project.MovieBookingApp.repository.TicketRepository;

@Service
public class MovieServiceImpl implements MovieService {
	
	@Autowired
	private MovieRepository movieRepo;
	private TicketRepository ticketRepo;
	
	@Override
	public List<Movie> getAllMovies() {
		// TODO Auto-generated method stub
		List<Movie> movielist = movieRepo.findAll();
		if(movielist != null && movielist.size()>0) {
			return movielist;
		}else {
		return null;}
	}

	@Override
	public Movie addMovie(Movie movie) throws MovieNameAlreadyExistsExceptions {
		// TODO Auto-generated method stub
		String newMovie= movie.getMovieName();
		
		Optional<Movie> opMovie = movieRepo.findById(movie.getMovieId());
		//if(newMovie.equals(opMovie.get()))
		if(opMovie.isPresent())
		{
			throw new MovieNameAlreadyExistsExceptions();
		}
		else
			return movieRepo.saveAndFlush(movie);
		
	}

	@Override
	public boolean updateMovie(Movie movie) {
		Movie movie1 = movieRepo.getById(movie.getMovieId());
		if(movie1 != null) {
			movie1.setSeatsBooked(movie.getSeatsBooked());
			movie1.setSeatsAvailable(movie.getSeatsAvailable());
			
			movieRepo.saveAndFlush(movie1);
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteMovie(int mid) {
		movieRepo.deleteById(mid);
		return true;
	}

	@Override
	public Movie getMovieById(int mid) {
		Optional<Movie> movie  = movieRepo.findById(mid);
		if(movie.isPresent()) {
			return movie.get();
		}
		return null;
	}

	@Override
	public Movie getMovieByName(String movieName) {
		// TODO Auto-generated method stub
		Movie movie = movieRepo.getMovieByName(movieName);
		return movie;
	}

	@Override
	public Movie getMovieByMnameAndTname(String mname, String Tname) {
		Movie movie = movieRepo.getMovieByMnameAndTname(mname, Tname);
		
		return movie;
	}
	

}
