package com.bugtracker.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.bugtracker.entity.Application;
import com.bugtracker.entity.Release;
import com.bugtracker.entity.Ticket;
import com.bugtracker.service.TicketService;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TicketControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private TicketService ticketService;

	@Test
	public void getAllTicket() throws Exception {

		/* setup mock */
		Application application = new Application("Trackzilla_04", "Williams", "A bug tracking application");
		Release release = new Release("Q1 Release Containing High Priority Bugs", "2030-02-16");
		List<Ticket> ticketList = Arrays.asList(new Ticket[] { new Ticket("Sort Feature",
				"Add the ability to sort tickets by severity", application, release, "OPEN") });
		when(ticketService.findAll()).thenReturn(ticketList);

		
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/tickets")
				.contentType(MediaType.APPLICATION_JSON);

		mockMvc.perform(requestBuilder).andExpect(status().isOk());
	}

	@Test
	public void saveTicketTest() throws Exception {

		/* setup mock */
		Application application = new Application("Trackzilla_04", "Williams", "A bug tracking application");
		Release release = new Release("Q1 Release Containing High Priority Bugs", "2030-02-16");
		Ticket ticket = new Ticket("Sort Feature","Add the ability to sort tickets by severity", application, release, "OPEN");
		
		when(ticketService.save(any(Ticket.class))).thenReturn(ticket);
		
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/v1/tickets")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"title\":\"Sort Feature\",\"description\":\"Add the ability to sort tickets by severity\","
						+ "\"application\":{\"id\":1,\"name\":\"Trackzilla\",\"description\":\"A bug tracking application\",\"owner\":\"Williams\"},"
						+ "\"release\":{\"id\":3,\"releaseDate\":\"2030-09-14\",\"description\":\"Q3 Release Containing Bugs\"},\"status\":\"OPEN\"}")
				.accept(MediaType.APPLICATION_JSON);
		mockMvc.perform(requestBuilder).andExpect(status().isOk());

	}

	@Test
	public void deleteTicketTest() throws Exception {
		
		/* setup mock */
		Application application = new Application("Trackzilla_04", "Williams", "A bug tracking application");
		Release release = new Release("Q1 Release Containing High Priority Bugs", "2030-02-16");
		Ticket ticket = new Ticket("Sort Feature","Add the ability to sort tickets by severity", application, release, "OPEN");
		
		when(ticketService.findById(3L)).thenReturn(ticket);
		
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/v1/tickets?id=3")
				.contentType(MediaType.APPLICATION_JSON);

		mockMvc.perform(requestBuilder).andExpect(status().isOk());

	}

}
