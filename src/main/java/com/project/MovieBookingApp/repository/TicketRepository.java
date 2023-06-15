package com.project.MovieBookingApp.repository;

import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.project.MovieBookingApp.model.Ticket;

@Repository
@Transactional
public interface TicketRepository extends JpaRepository<Ticket, Integer> {
		
	@Query(value="select t from Ticket t where t.movieName_fk = :movieName")
	public Set<Ticket> getMovieList(String movieName);
	
	@Query(value="select t from Ticket t where t.movieId_fk = :movieId")
	public Set<Ticket> getMovieListById(int movieId);
	
	@Modifying
	@Query(value="delete from Ticket where movieName_fk= :movieName")
	public void deleteTicketData(String movieName);
	
	@Modifying
	@Query(value="delete from Ticket where movieId_fk= :movieId")
	public void deleteTicketByMovieId(int movieId);
	

}
