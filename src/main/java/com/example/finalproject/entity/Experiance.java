package com.example.finalproject.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Experiance
{
	@Id
	@GeneratedValue
	private long id;

	@NotNull
	@Size(min = 1)
	private String experianceLevel;

	@ManyToMany(mappedBy = "experiances")
	private Set<Client> clients;

	public Experiance()
	{
	}

	public Experiance(String experianceLevel)
	{
		this.experianceLevel = experianceLevel;
		this.clients=new HashSet<Client>();
	}

	public long getId()
	{
		return id;
	}

	public void setId(long id)
	{
		this.id = id;
	}

	public String getExperianceLevel()
	{
		return experianceLevel;
	}

	public void setExperianceLevel(String experianceLevel)
	{
		this.experianceLevel = experianceLevel;
	}

	public Set<Client> getClients()
	{
		return clients;
	}

	public void setClients(Set<Client> clients)
	{
		this.clients = clients;
	}


}
