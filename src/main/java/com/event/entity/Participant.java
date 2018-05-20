package com.event.entity;

import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "STUDENTS")
@NamedQueries(@NamedQuery(name = "Participant.findAll", query = "SELECT p FROM Participant p ORDER BY p.id"))
public class Participant {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Long id;
	
	@Column(name = "FIRST_NAME", length = 256, nullable = false)
	private String firstName;
	
	@Column(name = "LAST_NAME", length = 256, nullable = false)
	private String lastName;
	
	@Column(name = "EMAIL", length = 256, nullable = false)
	private String email;

	@Column(name = "UNIVERSITY", length = 256, nullable = false)
	private String university; 
	
	@Column(name = "PROGRAM", length = 256, nullable = false)
	private String program;
	
	@Column(name = "YEAR_OF_EDUCATION")
	private Integer yearOfEducation;
	
	@ElementCollection
	@CollectionTable(name = "STUDENT_PREFERRED_LANGUAGES")
	private List<String> preferredLanguages;
	
	@Column(name = "INTERESTED_ABOUT", nullable = false)
	private String interestedAbout;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getUniversity() {
		return university;
	}

	public void setUniversity(String university) {
		this.university = university;
	}
	
	public String getProgram() {
		return program;
	}

	public void setProgram(String program) {
		this.program = program;
	}

	public Integer getYearOfEducation() {
		return yearOfEducation;
	}

	public void setYearOfEducation(Integer yearOfEducation) {
		this.yearOfEducation = yearOfEducation;
	}

	public List<String> getPreferredLanguages() {
		return preferredLanguages;
	}

	public void setPreferredLanguages(List<String> preferredLanguages) {
		this.preferredLanguages = preferredLanguages;
	}

	public String getInterestedAbout() {
		return interestedAbout;
	}

	public void setInterestedAbout(String interestedAbout) {
		this.interestedAbout = interestedAbout;
	}
	
}
