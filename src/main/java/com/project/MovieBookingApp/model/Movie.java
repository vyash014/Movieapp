package com.project.MovieBookingApp.model;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="movie")
public class Movie {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int movieId;
	
	@Column(name = "movieName")
	private String movieName;
	
	@Column(name = "theatreName")
	private String theatreName;
	
	@Column(name = "seatsAvailable")
	private int seatsAvailable;
	
	@Column(name = "seatsBooked")
	private int seatsBooked;
	
	@Column(name = "totalSeats")
	private int totalSeats;
	
//	@OneToMany(targetEntity= Ticket.class , cascade = CascadeType.ALL)
//	@JoinColumn(name="MT_FK", referencedColumnName = "movieId")
//	private List<Ticket> ticketList;
	
	public Movie(){
		
	}
	public Movie(String movieName, String theatreName, int seatsAvailable, int seatsBooked,int totalSeats) {
		super();
		this.movieName = movieName;
		this.theatreName = theatreName;
		this.seatsAvailable = seatsAvailable;
		this.seatsBooked = seatsBooked;
		this.totalSeats = totalSeats;
	}

	
}
