package com.bugtracker.service;

import java.util.List;

import com.bugtracker.entity.Ticket;

public interface TicketService {
	
	List<Ticket> findAll();
	Ticket findById(Long id);
	Ticket save(Ticket ticket);
	void delete(Long id);
}
