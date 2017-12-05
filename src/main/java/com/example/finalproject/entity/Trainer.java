package com.example.finalproject.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Trainer
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@NotNull
	@Size(min=1)
	private String firstName;

	@NotNull
	@Size(min=1)
	private String lastName;


	@NotNull
	@Size(min=1)
	private String contactNumber;

	@Column(name="email")
	private String email;

	@NotNull
	@Size(min=1)
	private String rating;

	@NotNull
	@Size(min=1)
	private String gender;


	@ManyToMany()
	private Set<Speciality>specialities;

	@ManyToMany()
	private Set<Location>locations;

	@ManyToMany()
	private Set<Experiance> experiances;

	@ManyToMany()
	private Set<Appointment> appointments;


	public Trainer()
	{
	}

	public Trainer(String firstName, String lastName, String contactNumber, String email, String rating, String gender)
	{
		this.firstName = firstName;
		this.lastName = lastName;
		this.contactNumber = contactNumber;
		this.email = email;
		this.rating = rating;
		this.gender = gender;
		this.specialities = new HashSet<Speciality>();
		this.locations = new HashSet<Location>();
		this.experiances=new HashSet<Experiance>();
		this.appointments=new HashSet<Appointment>();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
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

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
	public Set<Speciality> getSpecialities()
	{
		return specialities;
	}

	public void setSpecialities(Set<Speciality> specialities)
	{
		this.specialities = specialities;
	}

	public void addSpeciality(Speciality specialities) {this.specialities.add(specialities);}

	public void addLocation(Location locations) {this.locations.add(locations);}

	public void addExperiance(Experiance experiances) {this.experiances.add(experiances);}

	public void addAppointment(Appointment appointment){this.appointments.add(appointment);}



}
