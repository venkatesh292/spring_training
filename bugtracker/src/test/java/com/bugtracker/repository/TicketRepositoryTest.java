package com.bugtracker.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.bugtracker.entity.Application;
import com.bugtracker.entity.Release;
import com.bugtracker.entity.Ticket;

@RunWith(SpringRunner.class)
@DataJpaTest
public class TicketRepositoryTest {

	@Autowired
	TicketRepository ticketRepository;

	@Autowired
	ReleaseRepository releaseRepository;

	@Autowired
	ApplicationRepository applicationRepository;

	@Test
	public void testFindAllTickets() {

		Application application = applicationRepository
				.save(new Application("Trackzilla_04", "Williams", "A bug tracking application"));

		Release release = releaseRepository.save(new Release("Q1 Release Containing High Priority Bugs","2030-02-16"));

		List<Ticket> ticketList = Arrays.asList(new Ticket[] { new Ticket("Sort Feature",
				"Add the ability to sort tickets by severity", application, release, "OPEN") });

		List<Ticket> ticketSavedList = (List<Ticket>) ticketRepository.saveAll(ticketList);
		List<Ticket> tickets = (List<Ticket>) ticketRepository.findAll();

		assertThat(tickets.size()).isEqualTo(ticketSavedList.size());

	}

	@Test
	public void testFindTicketById() {

		Application application = applicationRepository
				.save(new Application("Trackzilla_04", "Williams", "A bug tracking application"));

		Release release = releaseRepository.save(new Release("Q1 Release Containing High Priority Bugs","2030-02-16"));

		Ticket ticket = ticketRepository.save(new Ticket("Sort Feature", "Add the ability to sort tickets by severity",
				application, release, "OPEN"));

		Ticket findTicket = ticketRepository.findById(ticket.getId()).get();

		assertThat(findTicket).isEqualTo(ticket);

	}

	@Test
	public void testSaveTicket() {

		Application application = applicationRepository
				.save(new Application("Trackzilla_04", "Williams", "A bug tracking application"));

		Release release = releaseRepository.save(new Release("Q1 Release Containing High Priority Bugs","2030-02-16"));

		Ticket ticket = ticketRepository.save(new Ticket("Sort Feature", "Add the ability to sort tickets by severity",
				application, release, "OPEN"));

		System.out.println(ticket);
		
		Ticket findTicket = ticketRepository.findById(ticket.getId()).get();

		assertThat(findTicket.getId().equals(ticket.getId()));
	}

	@Test
	public void testDeleteTicket() {

		Application application = applicationRepository
				.save(new Application("Trackzilla_04", "Williams", "A bug tracking application"));

		Release release = releaseRepository.save(new Release("Q1 Release Containing High Priority Bugs","2030-02-16"));

		Ticket ticket = ticketRepository.save(new Ticket("Sort Feature", "Add the ability to sort tickets by severity",
				application, release, "OPEN"));
		ticketRepository.delete(ticket);

		assertThat(ticketRepository.count()).isEqualTo(0);
	}
}
