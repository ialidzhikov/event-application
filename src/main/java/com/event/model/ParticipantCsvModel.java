package com.event.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "firstName", "lastName", "email", "university", "program", "yearOfEducation", "interestedAbout" })
public class ParticipantCsvModel {

	@JsonProperty("First Name")
	private String firstName;

	@JsonProperty("Last Name")
	private String lastName;

	@JsonProperty("Email")
	private String email;

	@JsonProperty("University")
	private String university;

	@JsonProperty("Program")
	private String program;

	@JsonProperty("Year of Education")
	private Integer yearOfEducation;

	@JsonProperty("Interested About")
	private String interestedAbout;

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

	public String getInterestedAbout() {
		return interestedAbout;
	}

	public void setInterestedAbout(String interestedAbout) {
		this.interestedAbout = interestedAbout;
	}
}
