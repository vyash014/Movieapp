package com.project.MovieBookingApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.project.MovieBookingApp.model.Movie;
import com.project.MovieBookingApp.model.Ticket;

import java.util.Set;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface MovieRepository extends JpaRepository<Movie, Integer> {
	
	@Query(value="select m from Movie m where m.movieName = :movieName")
	public Movie getMovieByName(String movieName);
	
	@Query(value="select m from Movie m where m.movieName = :movieName and m.theatreName =:theatreName")
	public Movie getMovieByMnameAndTname(String movieName, String theatreName);
}
