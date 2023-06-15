package com.project.MovieBookingApp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.project.MovieBookingApp.model.Movie;
import com.project.MovieBookingApp.model.Ticket;
import com.project.MovieBookingApp.repository.TicketRepository;
import com.project.MovieBookingApp.services.TicketServiceImpl;

@AutoConfigureMockMvc
@SpringBootTest
public class TicketServiceTest {
	
	@Mock
	private TicketRepository ticketRepo;
	
	@InjectMocks
	private TicketServiceImpl ticketService;
	
	@Autowired
	private MockMvc mockMvc;
	
	@BeforeEach
	public void init()
	{
		MockitoAnnotations.openMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(ticketService).build();
	}
	
	List<Ticket> ticketList = new ArrayList<Ticket>();
	
	@Test
	public void addTicketSuccess() throws Exception
	{
		Ticket ticket = new Ticket();
		ticket.setAvailableSeats(100);
		ticket.setBookedSeats(5);;
		ticket.setMovieName_fk("Test movie");
		ticket.setMovieId_fk(100);
		
		ticketList.add(ticket);
		when(ticketRepo.save(any())).thenReturn(ticket);
		System.out.println(ticket);
	//	Boolean t1 = ticketService.addTicket(ticket);
		assertTrue(ticketService.addTicket(ticket));
		
	}
	
	@Test
	public void getAllTicketSuccess() throws Exception
	{
		Ticket ticket = new Ticket();
		ticket.setMovieId_fk(100);
		ticket.setAvailableSeats(100);
		ticket.setBookedSeats(0);
		ticket.setMovieName_fk("Testmovie");
		ticketList.add(ticket);
		when(ticketRepo.findAll()).thenReturn(ticketList);
		
		List<Ticket> tList = ticketService.getTickets();
		assertEquals(ticketList, tList);
		
	}
	
	@Test
	public void getAllTicketFailure() throws Exception
	{
		
		when(ticketRepo.findAll()).thenReturn(null);
		
		List<Ticket> tList = ticketService.getTickets();
		assertNull(tList);
		
	}
	
	@Test
	public void deleteTicket() throws Exception
	{
		Ticket ticket = new Ticket("Test Movie2",0,0);
		ticket.setMovieId_fk(100);
		when(ticketRepo.save(ticket)).thenReturn(ticket);
		ticketService.deleteTicketByMovieId(100);
		assertEquals(true ,ticketService.deleteTicketByMovieId(100) );
	}
	
//	@Test
//	public void getAllTicketById() throws Exception
//	{
//		Ticket ticket = new Ticket("Test Movie2",0,0);
//		ticket.setMovieId_fk(100);
//		ticketList.add((Ticket) ticketService.getAllTicketsById(100));
//		when(ticketRepo.getMovieListById(100)).thenReturn(ticketRepo.getMovieListById(100));
//		assertEquals(1 ,ticketList.size());
//	
//	}
	

	

}
