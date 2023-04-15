package edu.tus.trackapp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.tus.trackapp.dto.Application;
import edu.tus.trackapp.dto.Release;

public interface ReleaseDAO extends JpaRepository<Release, Long> {

	List<Release> findAll();

	void addApplication(Application application, Long id);
	
	

}
