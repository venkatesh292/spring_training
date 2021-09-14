package com.bugtracker.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bugtracker.entity.Ticket;
import com.bugtracker.repository.TicketRepository;

@Service
public class TicketServiceImpl implements TicketService {

	@Autowired
	private TicketRepository ticketRepository;
	
	@Override
	public List<Ticket> findAll() {
		// TODO Auto-generated method stub
		return (List<Ticket>) ticketRepository.findAll();
	}

	@Override
	public Ticket findById(Long id) {
		
		 Optional<Ticket> findById = ticketRepository.findById(id);
		 
		   return findById.isPresent()? findById.get():null;
	}

	@Override
	public Ticket save(Ticket ticket) {
		
		return ticketRepository.save(ticket);
	}

	@Override
	public void delete(Long id) {
		
	  ticketRepository.deleteById(id);
		
	}

}
