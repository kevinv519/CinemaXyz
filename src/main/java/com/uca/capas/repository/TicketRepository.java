package com.uca.capas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uca.capas.domain.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {
	
}
