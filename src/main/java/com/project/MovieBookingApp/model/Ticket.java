package com.project.MovieBookingApp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="ticket")
public class Ticket {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int transactionId;
		
	private String movieName_fk;
	
	private int movieId_fk;
	
	private int availableSeats;
	
	private int bookedSeats;

	public Ticket(String movieName_fk, int availableSeats, int bookedSeats) {
		super();
		this.movieName_fk = movieName_fk;
		this.availableSeats = availableSeats;
		this.bookedSeats = bookedSeats;
	}

	public Ticket() {
		// TODO Auto-generated constructor stub
	}
}
