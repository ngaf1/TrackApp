package edu.tus.trackapp.controllers;
import edu.tus.trackapp.dto.Application;
import edu.tus.trackapp.repositories.TrackappRepo;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;


@RestController
@Service
@RequestMapping("/tracker/v1")
public class TrackappController {

    @Autowired
    TrackappRepo appRepo;
    
    @GetMapping(value="/applications")
	public
	List<Application> getApplicationForCategory() {
		return appRepo.findAll();}
	
	@GetMapping("/applications/{id}")
	public 
	Optional<Application> getApplication(@PathVariable("id") Long id) { 
		return appRepo.findById(id); }

	@GetMapping("/applications/name/{name}")
	public 
	Optional<Application> getApplication(@PathVariable("name") String name) { 
		return appRepo.findByName(name); }
	
	@GetMapping("/applications/owner/{owner}")
	public 
	Optional<Application> getAppbyOwner(@PathVariable("owner") String owner) { 
		return appRepo.findByOwner(owner); }

	@PostMapping(value = "/application")
	public
	ResponseEntity<Application> insertApplication(@RequestBody Application application) {
		Application savedApplication = appRepo.save(application);
		return new ResponseEntity<>(savedApplication, HttpStatus.OK);}
	
	
	/*
	 * @DeleteMapping(value="/application/{id}") public ResponseEntity<Application>
	 * deleteApplication(@PathVariable("id") Long id) { Optional<Application>
	 * optionalApplication = appRepo.findById(id); Application existingApplication =
	 * optionalApplication.get(); appRepo.delete(existingApplication); return new
	 * ResponseEntity<>(HttpStatus.NO_CONTENT);}
	 * 
	 * @PutMapping(value="/applicatione/{id}") public ResponseEntity<Application>
	 * updateApplication(@PathVariable("id") Long id, @RequestBody Application
	 * application) { Optional<Application> optionalApplication =
	 * appRepo.findById(id); Application existingApplication =
	 * optionalApplication.get(); existingApplication.setId(application.getId());
	 * existingApplication.setName(application.getName());
	 * existingApplication.setDescription(application.getDescription());
	 * existingApplication.setOwner(application.getOwner()); appRepo.flush();
	 * 
	 * Application savedApplication = appRepo.save(existingApplication) ; return new
	 * ResponseEntity<>(savedApplication, HttpStatus.OK) ;}
	 */
	
}

