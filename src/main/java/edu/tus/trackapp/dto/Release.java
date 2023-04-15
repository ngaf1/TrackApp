package edu.tus.trackapp.dto;

import static javax.persistence.CascadeType.PERSIST;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.tomcat.util.http.parser.MediaType;

import com.fasterxml.jackson.annotation.JsonFormat;


@Entity
@Table(name = "releases")
public class Release {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate release_date;
    
    private String description;

    @OneToMany(cascade = PERSIST)
    private List<Application> applications = new ArrayList<>();

    public Release() {
    }

    public Release(Long id, LocalDate releaseDate, String description, List<Application> applications) {
        this.id = id;
        this.release_date = releaseDate;
        this.description = description;
        this.applications = applications;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getReleaseDate() {
        return release_date;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.release_date = releaseDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Application> getApplications() {
        return applications;
    }

    public void setApplications(List<Application> applications) {
        this.applications = applications;
    }

    public void addApplication(Application application) {
        this.applications.add(application);
    }
}
