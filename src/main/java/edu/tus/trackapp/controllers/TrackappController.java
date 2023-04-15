package edu.tus.trackapp.controllers;
import edu.tus.trackapp.dto.Application;
import edu.tus.trackapp.dto.Release;
import edu.tus.trackapp.dto.Ticket;
import edu.tus.trackapp.repositories.ReleaseDAO;
import edu.tus.trackapp.repositories.TicketDAO;
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
    
    @Autowired
    TicketDAO tickRepo;
    
    @Autowired
    ReleaseDAO relRepo;

	@GetMapping(value="/applications")
	List<Application> getApplicationForCategory() {
		return appRepo.findAll();}
	
	@GetMapping(value="/tickets")
	List<Ticket> getTicketsForCategory() {
		return tickRepo.findAll();}
	
	@GetMapping(value="/releases")
	List<Release> getReleaseForCategory() {
		return relRepo.findAll();}

	@GetMapping("/applications/{id}") 
	Optional<Application> getApplication(@PathVariable("id") Long id) { 
		return appRepo.findById(id); }

	@GetMapping("/applications/name/{name}") 
	Optional<Application> getApplication(@PathVariable("name") String name) { 
		return appRepo.findByName(name); }
	
	@GetMapping("/applications/owner/{owner}") 
	Optional<Application> getAppbyOwner(@PathVariable("owner") String owner) { 
		return appRepo.findByOwner(owner); }

	@PostMapping(value = "/application")
	ResponseEntity<Application> insertApplication(@RequestBody Application application) {
		Application savedApplication = appRepo.save(application);
		return new ResponseEntity<>(savedApplication, HttpStatus.OK);}
	
	@PostMapping(value = "/release")
	ResponseEntity<Release> insertRelease(@RequestBody Release release) {
		Release savedRelease = relRepo.save(release);
		return new ResponseEntity<>(savedRelease, HttpStatus.OK);}

	@DeleteMapping(value="/application/{id}")
	ResponseEntity<Application> deleteApplication(@PathVariable("id") Long id) {
		Optional<Application> optionalApplication = appRepo.findById(id); 
		Application existingApplication = optionalApplication.get();
		appRepo.delete(existingApplication);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);}

	@PutMapping(value="/applicatione/{id}")
	ResponseEntity<Application> updateApplication(@PathVariable("id") Long id, @RequestBody Application application) {
		Optional<Application> optionalApplication = appRepo.findById(id); 
		Application existingApplication = optionalApplication.get();
		existingApplication.setId(application.getId());
		existingApplication.setName(application.getName());
		existingApplication.setDescription(application.getDescription());
		existingApplication.setOwner(application.getOwner());
		appRepo.flush();

		Application savedApplication = appRepo.save(existingApplication) ;
		return new ResponseEntity<>(savedApplication, HttpStatus.OK) ;}
	
	/*
	 * @PutMapping("/release/{appid}/{releaseid}") public ResponseEntity<Void>
	 * addApptoRelease(@PathVariable("appid") Integer
	 * appid, @PathVariable("releaseid") Integer releaseid, UriComponentsBuilder
	 * builder) { relRepo.addApplication(appid, releaseid); return new
	 * ResponseEntity<Void>(HttpStatus.OK); }
	 */
}

