package com.bugtracker.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.bugtracker.entity.Application;
import com.bugtracker.entity.Release;
import com.bugtracker.entity.Ticket;
import com.bugtracker.exception.RestRequestException;
import com.bugtracker.service.TicketService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("api/v1/tickets")
public class TicketController {

	@Autowired
	private TicketService ticketService;

	private static final Logger logger = LogManager.getLogger(TicketController.class.getName());

	@GetMapping
	@ApiOperation(value = "Get All Ticket", notes = "Provide complete ticket details", response = List.class)
	public ResponseEntity<List<Ticket>> getAllTickets() throws RestRequestException{

		List<Ticket> tickets = null;

		logger.info("Inside TicketController.getAllTicket");
	
			if (null != (tickets = ticketService.findAll())) {

				logger.info(" TicketController.getAllTicket tickets: {}", tickets);
				return new ResponseEntity<List<Ticket>>(tickets, HttpStatus.OK);
			} else {
				logger.info(" TicketController.getAllTicket tickets: {}", tickets);
				throw new RestRequestException(HttpStatus.NO_CONTENT,"30004","No content available");
			}
	}

	@GetMapping("/{id}")
	@ApiOperation(value = "Find ticket by id", notes = "Provide id to look up specific ticket deatil", response = Ticket.class)
	public ResponseEntity<Ticket> findTicketByID(
			@ApiParam(value = "Id value of the ticket need to be retrieve", required = true) @PathVariable("id") Long ticketId) throws RestRequestException {

		logger.info("Inside TicketController.findTicketByID, Id: {}", ticketId);

		Ticket ticket = null;
		
			if (null != (ticket = ticketService.findById(ticketId))) {
				logger.info("TicketController.findTicketByID, ticket: {}", ticket);
				return new ResponseEntity<>(ticket, HttpStatus.OK);
			} else {
				logger.info(" TicketController.findTicketByID ticket: {}", ticket);
				throw new RestRequestException(HttpStatus.NOT_FOUND,"30006","Ticket details not found");
			}

	}

	@PostMapping
	@ApiOperation(value = "Add ticket", notes = "Adding  a new ticket", response = Ticket.class)
	public ResponseEntity<Ticket> submitForm(
			@ApiParam(value = "Required to provide a ticket details", required = true) @RequestBody Ticket ticket) throws RestRequestException {

		Ticket ticketObj = null;
		
			logger.info(" Inside TicketController.submitForm, ticket: {} ", ticket);

			if (null != ticket && null != (ticketObj = ticketService.save(ticket))) {

				logger.info("TicketController.submitForm  after adding the ticket: {}", ticketObj);
				return new ResponseEntity<Ticket>(ticketObj, HttpStatus.OK);
			} else {
				logger.info("TicketController.submitForm  after adding ticket: {}", ticketObj);
				throw new RestRequestException(HttpStatus.BAD_REQUEST,"30008","Not able to save the ticket");
			}
	}

	@PutMapping
	@ApiOperation(value = "Update Ticket", notes = " Update the existing ticket for the provided id ", response = Ticket.class)
	public ResponseEntity<Ticket> updateTicket(
			@ApiParam(value = "Ticket details need to update", required = true) @RequestBody Ticket ticket) throws RestRequestException {
		Ticket ticketObj = null;
		
			logger.info("TicketController.updateTicket requested ticket object to update : {}", ticket);

			if (null != ticket && null != ticket.getId()) {
				
				Ticket ticketSave = ticketService.save(ticketObj);

				if (null != ticketSave) {
					logger.info("TicketController.updateTicket saved ticket Entity : {}", ticketSave);
					return new ResponseEntity<Ticket>(ticketSave, HttpStatus.OK);
				} else {
					logger.info("TicketController.updateTicket saved entity : {}", ticketSave);
					throw new RestRequestException(HttpStatus.BAD_REQUEST,"30009","Not able to update the ticket");
				}
			} else {
				logger.info("TicketController.updateTicket Inavlid update request");
				throw new RestRequestException(HttpStatus.NOT_FOUND,"30010","Inavlid update request");
			}
	}
	/*
	 * @PutMapping
	 * 
	 * @ApiOperation(value = "Save or update ticket", notes =
	 * " Save or update the ticket", response = Ticket.class) public
	 * ResponseEntity<Ticket> saveOrUpdateTicket(
	 * 
	 * @ApiParam(value = "Ticket deatils need to update", required =
	 * true) @RequestBody Ticket ticket) {
	 * 
	 * try { logger.
	 * info(" Inside TicketController.saveOrUpdateTicket, ticket to save or update: {} "
	 * , ticket);
	 * 
	 * Ticket ticketObj = null;
	 * 
	 * if (null != ticket && null != (ticketObj = ticketService.save(ticket))) {
	 * 
	 * logger.info("TicketController.saveOrUpdateTicket  after updating ticket: {}",
	 * ticketObj); return new ResponseEntity<Ticket>(ticketObj, HttpStatus.OK); }
	 * else {
	 * logger.info("TicketController.saveOrUpdateTicket  after updating ticket: {}",
	 * ticketObj); throw new RestRequestException("Invalid ticket object"); } }
	 * catch (RestRequestException ex) { logger.error("Exception: ", ex); return new
	 * ResponseEntity<>(HttpStatus.NO_CONTENT); } catch (Exception ex) {
	 * logger.error("Exception: ", ex); return new
	 * ResponseEntity<>(HttpStatus.BAD_REQUEST); } }
	 */

	@DeleteMapping("/{id}")
	@ApiOperation(value = "Delete ticket", notes = "Delete a ticket", response = HttpStatus.class)
	public ResponseEntity<Object> deleteTicket(
			@ApiParam(value = "Id value of the ticket need to be delete", required = true) @PathVariable("id") Long ticketId) throws RestRequestException {

		logger.info("Inside TicketController.deleteTicket, Id: {}", ticketId);

		Ticket ticket = null;

			if (null != (ticket = ticketService.findById(ticketId))) {
				logger.info("TicketController.deleteTicket, ticket: {}", ticket);
				ticketService.delete(ticketId);
				return new ResponseEntity<>(HttpStatus.OK);
			} else {
				logger.info(" TicketController.deleteTicket ticket: {}", ticket);
				throw new RestRequestException(HttpStatus.NOT_FOUND,"30020","Inavlid delete request");
			}

	}
}
