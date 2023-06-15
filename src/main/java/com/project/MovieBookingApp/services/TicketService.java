package com.project.MovieBookingApp.services;

import java.util.List;
import java.util.Set;

import com.project.MovieBookingApp.model.Ticket;


public interface TicketService {
		
	public Set<Ticket> getAllTickets(String movieName_fk);
	public Set<Ticket> getAllTicketsById(int movieId_fk);
	public boolean addTicket(Ticket ticket);
	public boolean deleteTicket(int transactionId);
	public boolean deleteTicketByMovieId(int movieId_fk);
	public List<Ticket> getTickets();
}
