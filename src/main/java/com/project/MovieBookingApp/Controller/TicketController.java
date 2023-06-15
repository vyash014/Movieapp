package com.project.MovieBookingApp.Controller;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.MovieBookingApp.model.Movie;
import com.project.MovieBookingApp.model.Ticket;
import com.project.MovieBookingApp.response.ResponseHandler;
import com.project.MovieBookingApp.services.MovieService;
import com.project.MovieBookingApp.services.TicketService;

@CrossOrigin
@RestController
@RequestMapping("api/v1/ticket")
public class TicketController 
{
	@Autowired
	private MovieService ms;
	
	@Autowired
	private TicketService ts;
	
	@GetMapping("/getTickets")
	public ResponseEntity<?> getAllMovie()
	{
		List<Ticket> ticketlist = ts.getTickets();
		System.out.println(ticketlist);
		if(ticketlist !=null)
		{
			
		return ResponseHandler.generateResponse("Succesfully fetched the ticketlist", HttpStatus.OK, ticketlist);
		}
		return new ResponseEntity<String>("ticketlist is empty", HttpStatus.NO_CONTENT);
		
	}
	
	@GetMapping("/getAllTickets/{movieName}")
	public ResponseEntity<?> getAllTickets(@PathVariable("movieName") String movieName)
	{
		Set<Ticket> ticketList = ts.getAllTickets(movieName);
		
		System.out.println(ticketList);
		if(ticketList !=null)
		{			
			CacheControl cacheControlObj = CacheControl.maxAge(30, TimeUnit.MINUTES);
			return ResponseEntity.ok().cacheControl(cacheControlObj)
					.body(ResponseHandler.generateResponse("Succesfully fetched the Ticketlist", HttpStatus.OK, ticketList));
		}
		return new ResponseEntity<String>("TicketList is empty", HttpStatus.NO_CONTENT);
		
	}
	
	@GetMapping("/getAllTicketsById/{movieId}")
	public ResponseEntity<?> getAllTicketsById(@PathVariable("movieId") int movieId)
	{
		Set<Ticket> ticketList = ts.getAllTicketsById(movieId);
		
		System.out.println(ticketList);
		if(ticketList !=null)
		{			
			CacheControl cacheControlObj = CacheControl.maxAge(30, TimeUnit.MINUTES);
			return ResponseEntity.ok().cacheControl(cacheControlObj)
					.body(ResponseHandler.generateResponse("Succesfully fetched the Ticketlist", HttpStatus.OK, ticketList));
		}
		return new ResponseEntity<String>("TicketList is empty", HttpStatus.NO_CONTENT);
		
	}
	
	@PostMapping("/add/{mid}")    // @RequestParam
	public ResponseEntity<?> addTicket(@PathVariable("mid") int mid, @RequestBody Ticket ticket)
	{
		Movie movieexists = ms.getMovieById(mid);
		if(movieexists!=null)
		{	
			System.out.println(movieexists);
			ticket.setMovieName_fk(movieexists.getMovieName());
			ticket.setMovieId_fk(mid);
			ticket.setBookedSeats(ticket.getBookedSeats());
			int bookedseats = movieexists.getSeatsBooked();
			int totalbooked = bookedseats + ticket.getBookedSeats();
			int avaseats = movieexists.getTotalSeats() - totalbooked;
			 
			ticket.setAvailableSeats(avaseats);
			movieexists.setSeatsAvailable(avaseats);
			movieexists.setSeatsBooked(totalbooked);
			if(ts.addTicket(ticket) && ms.updateMovie(movieexists))
			{
				return new ResponseEntity<Ticket>(ticket, HttpStatus.CREATED);
			}
			
			
		}
		return new ResponseEntity<String>("Ticket cannot be added", HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	
	
	
	@PostMapping("/adds/{movieName}")
	public ResponseEntity<?> addTicket(@PathVariable("movieName") String movieName, @RequestBody Ticket ticket){
		
		Movie movieexists = ms.getMovieByName(movieName);
		System.out.println(movieName);
		
		if(movieexists!=null) 
		{
			
			ticket.setMovieName_fk(movieexists.getMovieName());
			ticket.setMovieId_fk(movieexists.getMovieId());
			ticket.setAvailableSeats(movieexists.getSeatsAvailable());
			ticket.setBookedSeats(ticket.getBookedSeats());
			
			if(ts.addTicket(ticket))
			{
				return new ResponseEntity<Ticket>(ticket, HttpStatus.CREATED);
			}
		}
		return new ResponseEntity<String>("Ticket cannot be added", HttpStatus.INTERNAL_SERVER_ERROR);
							
	}
	
	
	@DeleteMapping("/delete/{tid}")
	public ResponseEntity<?> deleteTicket(@PathVariable ("tid") int tid)
	{
		if(ts.deleteTicket(tid))
		{
			return new ResponseEntity<String>("Ticket got deleted successfully",HttpStatus.OK);
		}
		
		return new ResponseEntity<String>("Ticket did not get deleted ",HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@DeleteMapping("/deleteByMovieId/{mid}")
	public ResponseEntity<?> deleteTicketByMovieId(@PathVariable ("mid") int mid)
	{
		if(ts.deleteTicketByMovieId(mid))
		{
			return new ResponseEntity<String>("Ticket got deleted successfully",HttpStatus.OK);
		}
		
		return new ResponseEntity<String>("Ticket did not get deleted ",HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
