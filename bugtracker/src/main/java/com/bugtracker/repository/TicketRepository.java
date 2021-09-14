package com.bugtracker.repository;

import org.springframework.data.repository.CrudRepository;

import com.bugtracker.entity.Ticket;

public interface TicketRepository extends CrudRepository<Ticket,Long> {

}
