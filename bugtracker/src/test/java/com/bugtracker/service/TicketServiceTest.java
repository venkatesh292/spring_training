package com.bugtracker.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.bugtracker.entity.Application;
import com.bugtracker.entity.Release;
import com.bugtracker.entity.Ticket;
import com.bugtracker.repository.TicketRepository;

public class TicketServiceTest {

	@InjectMocks
	private TicketService ticketService = new TicketServiceImpl();

	@Mock
	private TicketRepository ticketRepository;

	@BeforeEach
	void init() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void getAllTickets() {

		/* setup mock */
		Application application = new Application("Trackzilla_04", "Williams", "A bug tracking application");
		Release release = new Release("Q1 Release Containing High Priority Bugs", "2030-02-16");
		List<Ticket> ticketList = Arrays.asList(new Ticket[] { new Ticket("Sort Feature",
				"Add the ability to sort tickets by severity", application, release, "OPEN") });
		when(ticketRepository.findAll()).thenReturn(ticketList);

		assertEquals(ticketList.size(), ticketService.findAll().size());

	}

	@Test
	public void saveTicket() {
		/* setup mock */
		Application application = new Application("Trackzilla_04", "Williams", "A bug tracking application");
		Release release = new Release("Q1 Release Containing High Priority Bugs", "2030-02-16");
		Ticket ticket = new Ticket("Sort Feature", "Add the ability to sort tickets by severity", application, release,
				"OPEN");

		when(ticketRepository.save(any(Ticket.class))).thenReturn(ticket);

		Ticket saveTicket = ticketService.save(ticket);

		assertEquals(ticket, saveTicket);
	}

	@Test
	public void deleteTicket() {

		
		ticketService.delete(1L);
		verify(ticketRepository).deleteById(1L);

	}
}
