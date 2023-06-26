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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.project.MovieBookingApp.exception.MovieNameAlreadyExistsExceptions;
import com.project.MovieBookingApp.model.Movie;
import com.project.MovieBookingApp.model.Ticket;
import com.project.MovieBookingApp.repository.TicketRepository;
import com.project.MovieBookingApp.response.ResponseHandler;
import com.project.MovieBookingApp.services.MovieService;
import com.project.MovieBookingApp.services.PublisherService;
import com.project.MovieBookingApp.services.TicketService;

@CrossOrigin
@RestController
@RequestMapping("api/v1")
public class MovieController {
	
	String baseUrl ="http://localhost:8082/api/v1";
	
	@Autowired
	private MovieService movieService;
	
	@Autowired
	private TicketService ticketService;
	
	@Autowired
	TicketRepository ticketRepo;
	
	@Autowired
	private PublisherService publishservice;
	
	
	@GetMapping("/getAllMovies")
	public ResponseEntity<?> getAllMovie()
	{
		List<Movie> movielist = movieService.getAllMovies();
		System.out.println(movielist);
		if(movielist !=null)
		{
		
		return ResponseHandler.generateResponse("Succesfully fetched the movielist", HttpStatus.OK, movielist);
			
		}
		return new ResponseEntity<String>("movielist is empty", HttpStatus.NO_CONTENT);
		
	}
	
	@PostMapping("/addMovie")
	public ResponseEntity<?> addMovie(@RequestBody Movie movie) throws MovieNameAlreadyExistsExceptions
	{
		if(movieService.addMovie(movie)!=null)
		{	
			publishservice.setTemp(movie.getMovieName()+" Added Successfully");	
			return new ResponseEntity<Movie>(movie, HttpStatus.CREATED);
		}
		
		publishservice.setTemp(movie.getMovieName()+"not added");
		return new ResponseEntity<String>("movie object is null", HttpStatus.NO_CONTENT);
	}
	
	@DeleteMapping("/delete/{mid}")
	public ResponseEntity<?> deleteMovie(@PathVariable ("mid") int mid)
	{	
		Movie movieexists = movieService.getMovieById(mid);
		if(movieexists !=null) {
		if(movieService.deleteMovie(mid))
		{	
			int movieId_fk = mid;
			ticketService.deleteTicketByMovieId(movieId_fk);
			//ticketRepo.deleteTicketByMovieId(movieId_fk);
			publishservice.setTemp(mid +" Deleted Successfully");	
			return new ResponseEntity<String>("Movie got deleted successfully",HttpStatus.OK);
		}
		publishservice.setTemp(mid+" NOT deleted Successfully");
		return new ResponseEntity<String>("Movie did not get deleted ",HttpStatus.INTERNAL_SERVER_ERROR);}
		
		else {return new ResponseEntity<String>("Movie did not get deleted ",HttpStatus.INTERNAL_SERVER_ERROR);}
	}
	
	@PutMapping("/updateMovie")
	public ResponseEntity<?> updateMovie(@RequestBody Movie movie)
	{
		if(movieService.updateMovie(movie))
		{	
			publishservice.setTemp(movie.getMovieName() +" Updated Successfully");
			System.out.println("updateddd movie");
			return new ResponseEntity<String>("Movie got updated successfully",HttpStatus.OK);
		}
		publishservice.setTemp(movie.getMovieName() +" Update Failure");
		System.out.println("updateddd movie");
		return new ResponseEntity<String>("Movie updation failed",HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@GetMapping("/movieById/{mid}")
	public ResponseEntity<?> getMovieById(@PathVariable("mid") int mid)
	{
		Movie movieexists = movieService.getMovieById(mid);
		if(movieexists !=null)
		{	
			publishservice.setTemp(movieexists.getMovieId() +" Got movie record Successfully");
			return new ResponseEntity<Movie>(movieexists, HttpStatus.OK);
		}
		publishservice.setTemp(mid +" Unable to get movie record");
		return new ResponseEntity<String>("Movie record does not exist",HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/movieByName/{mname}")
	public ResponseEntity<?> getMovieByName(@PathVariable("mname") String mname)
	{
		Movie movieexists = movieService.getMovieByName(mname);
		if(movieexists !=null)
		{	
			System.out.println("Not null");
			return new ResponseEntity<Movie>(movieexists, HttpStatus.OK);
		}
		else {System.out.println("null");
		return new ResponseEntity<String>("Movie record does not exist",HttpStatus.NO_CONTENT);}
	}
	
	@RequestMapping(value = "/moviebymandtname/{mname}/{tname}", method=RequestMethod.GET)
	public ResponseEntity<?> getMovieByMnameAndTname(@PathVariable("mname") String mname, @PathVariable("tname") String tname)
	{
		Movie movieexists = movieService.getMovieByMnameAndTname(mname,tname);
		if(movieexists !=null)
		{
			return new ResponseEntity<Movie>(movieexists, HttpStatus.OK);
		}
		return new ResponseEntity<String>("Movie record does not exist",HttpStatus.NO_CONTENT);
		
	}
	
}
