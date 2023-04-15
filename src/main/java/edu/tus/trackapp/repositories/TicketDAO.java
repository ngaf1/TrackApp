package edu.tus.trackapp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.tus.trackapp.dto.Ticket;

public interface TicketDAO extends JpaRepository<Ticket, Long>{

	List<Ticket> findAll();
	
	

}
