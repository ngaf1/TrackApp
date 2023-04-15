package edu.tus.trackapp.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.tus.trackapp.dto.Application;

@Repository
public interface TrackappRepo extends JpaRepository<Application, Long> {
	
	Optional<Application> findByName(String name);
	Optional<Application> findByOwner(String owner);
	Optional<Application> findById(Long id);
	
}
