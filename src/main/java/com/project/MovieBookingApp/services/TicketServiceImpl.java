package com.project.MovieBookingApp.services;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.MovieBookingApp.model.Movie;
import com.project.MovieBookingApp.model.Ticket;
import com.project.MovieBookingApp.repository.TicketRepository;

@Service
public class TicketServiceImpl implements TicketService {

	@Autowired
	TicketRepository ticketRepo;
	
	@Override
	public Set<Ticket> getAllTickets(String movieName_fk) {
		// TODO Auto-generated method stub
		Set<Ticket> ticketList = ticketRepo.getMovieList(movieName_fk);
		
		return ticketList;
	}

	@Override
	public boolean addTicket(Ticket ticket) {
		
		Ticket ticketObj = new Ticket();
		
		ticketObj.setMovieName_fk(ticket.getMovieName_fk());
		ticketObj.setMovieId_fk(ticket.getMovieId_fk());
		ticketObj.setAvailableSeats(ticket.getAvailableSeats());
		ticketObj.setBookedSeats(ticket.getBookedSeats());
		ticketRepo.saveAndFlush(ticketObj);
		return true;
	}

	@Override
	public boolean deleteTicket(int transactionId) {
		ticketRepo.deleteById(transactionId);
		return true;
	}

	@Override
	public Set<Ticket> getAllTicketsById(int movieId_fk) {
		Set<Ticket> ticketList = ticketRepo.getMovieListById(movieId_fk);
		
		return ticketList;
	}

	@Override
	public boolean deleteTicketByMovieId(int movieId_fk) {
		Set<Ticket> ticketList = ticketRepo.getMovieListById(movieId_fk);
		if(ticketList!=null) {
		ticketRepo.deleteTicketByMovieId(movieId_fk);
		}
		return true;
	}

	@Override
	public List<Ticket> getTickets() {
		// TODO Auto-generated method stub
		List<Ticket> ticketlist = ticketRepo.findAll();
		if(ticketlist != null && ticketlist.size()>0) {
			return ticketlist;
		}else {
		return null;}
	}
	
	

}
