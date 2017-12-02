package com.example.finalproject.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

public class Trainer
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@NotNull
	@Size(min=1)
	private String firstname;

	@NotNull
	@Size(min=1)
	private String lastname;


	@NotNull
	@Size(min=1)
	private String contactnumber;

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

	public Trainer()
	{
	}

	public Trainer(String firstname, String lastname, String contactnumber, String email)
	{
		this.firstname = firstname;
		this.lastname = lastname;
		this.contactnumber = contactnumber;
		this.email = email;
		this.specialities = new HashSet<Speciality>();
	}


	public long getId()
	{
		return id;
	}

	public void setId(long id)
	{
		this.id = id;
	}

	public String getFirstname()
	{
		return firstname;
	}

	public void setFirstname(String firstname)
	{
		this.firstname = firstname;
	}

	public String getLastname()
	{
		return lastname;
	}

	public void setLastname(String lastname)
	{
		this.lastname = lastname;
	}

	public String getContactnumber()
	{
		return contactnumber;
	}

	public void setContactnumber(String contactnumber)
	{
		this.contactnumber = contactnumber;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public String getRating()
	{
		return rating;
	}

	public void setRating(String rating)
	{
		this.rating = rating;
	}

	public String getGender()
	{
		return gender;
	}

	public void setGender(String gender)
	{
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
}
