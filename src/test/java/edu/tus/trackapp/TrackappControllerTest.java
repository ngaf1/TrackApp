package edu.tus.trackapp;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assert;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import edu.tus.trackapp.controllers.TrackappController;
import edu.tus.trackapp.dto.Application;
import edu.tus.trackapp.repositories.TrackappRepo;

//@RunWith(MockitoJUnitRunner.class)
public class TrackappControllerTest {

    @InjectMocks
    private TrackappController trackappController;

    @Mock
    private TrackappRepo appRepo;
    
    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetApplicationForCategory() {
        Application application = new Application(1L, "Test App", "This is a test app", "Test Owner");
        List<Application> applicationList = Arrays.asList(application);

        when(appRepo.findAll()).thenReturn(applicationList);

        List<Application> result = trackappController.getApplicationForCategory();

        assertEquals(applicationList, result);
    }   

    @Test
    public void testGetApplicationById() {
        Application application = new Application(1L, "Test App", "This is a test app", "Test Owner");

        when(appRepo.findById(1L)).thenReturn(Optional.of(application));

        Optional<Application> result = trackappController.getApplication(1L);

        assertEquals(Optional.of(application), result);
    }

    @Test
    public void testGetApplicationByName() {
        Application application = new Application(1L, "Test App", "This is a test app", "Test Owner");

        when(appRepo.findByName("Test App")).thenReturn(Optional.of(application));

        Optional<Application> result = trackappController.getApplication("Test App");

        assertEquals(Optional.of(application), result);
    }

    @Test
    public void testGetApplicationByOwner() {
        Application application = new Application(1L, "Test App", "This is a test app", "Test Owner");

        when(appRepo.findByOwner("Test Owner")).thenReturn(Optional.of(application));

        Optional<Application> result = trackappController.getAppbyOwner("Test Owner");

        assertEquals(Optional.of(application), result);
    }

    @Test
    public void testInsertApplication() {
        Application application = new Application(1L, "Test App", "This is a test app", "Test Owner");

        when(appRepo.save(application)).thenReturn(application);

        ResponseEntity<Application> result = trackappController.insertApplication(application);

        assertEquals(application, result.getBody());
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }
    
	/*
	 * @Test public void deleteApplicationTest() throws Exception { // Create an
	 * application to be deleted Application appToDelete = new Application();
	 * appToDelete.setName("Test App");
	 * appToDelete.setDescription("Test App Description");
	 * appToDelete.setOwner("Test Owner"); appRepo.save(appToDelete);
	 * 
	 * // Delete the application
	 * mockMvc.perform(MockMvcRequestBuilders.delete("/tracker/v1/application/" +
	 * appToDelete.getId()))
	 * .andExpect(MockMvcResultMatchers.status().isNoContent());
	 * 
	 * // Verify that the application was deleted
	 * assertFalse(appRepo.existsById(appToDelete.getId())); }
	 * 
	 * @Test public void updateApplicationTest() throws Exception { // Create an
	 * application to be updated Application existingApp = new Application();
	 * existingApp.setName("Existing App");
	 * existingApp.setDescription("Existing App Description");
	 * existingApp.setOwner("Existing Owner"); appRepo.save(existingApp);
	 * 
	 * // Create an updated application Application updatedApp = new Application();
	 * updatedApp.setId(existingApp.getId()); updatedApp.setName("Updated App");
	 * updatedApp.setDescription("Updated App Description");
	 * updatedApp.setOwner("Updated Owner");
	 * 
	 * // Update the application
	 * mockMvc.perform(MockMvcRequestBuilders.put("/tracker/v1/applicatione/" +
	 * existingApp.getId()) .contentType(MediaType.APPLICATION_JSON) .content(new
	 * ObjectMapper().writeValueAsString(updatedApp)))
	 * .andExpect(MockMvcResultMatchers.status().isOk())
	 * .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(existingApp.getId()))
	 * .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(updatedApp.getName(
	 * )))
	 * .andExpect(MockMvcResultMatchers.jsonPath("$.description").value(updatedApp.
	 * getDescription()))
	 * .andExpect(MockMvcResultMatchers.jsonPath("$.owner").value(updatedApp.
	 * getOwner()));
	 * 
	 * // Verify that the application was updated Application savedApp =
	 * appRepo.findById(existingApp.getId()).get();
	 * assertEquals(existingApp.getId(), savedApp.getId());
	 * assertEquals(updatedApp.getName(), savedApp.getName());
	 * assertEquals(updatedApp.getDescription(), savedApp.getDescription());
	 * assertEquals(updatedApp.getOwner(), savedApp.getOwner()); }
	 */
    
    @Test
    public void testId() {
        Application app = new Application();
        assertNull(app.getId());
        appRepo.save(app);
        assertNotNull(app.getId());
    }

    @Test
    public void testName() {
        Application app = new Application();
        app.setName("Test Application");
        assertEquals("Test Application", app.getName());
    }

    @Test
    public void testDescription() {
        Application app = new Application();
        app.setDescription("This is a test application");
        assertEquals("This is a test application", app.getDescription());
    }

    @Test
    public void testOwner() {
        Application app = new Application();
        app.setOwner("John Doe");
        assertEquals("John Doe", app.getOwner());
    }

    @Test
    public void testToString() {
        Application app = new Application();
        app.setId(1L);
        app.setName("Test Application");
        app.setOwner("John Doe");
        app.setDescription("This is a test application");
        String expected = "Application{id=1, name='Test Application', owner=John Doe, description='This is a test application'}";
        assertEquals(expected, app.toString());
    }
  
}

