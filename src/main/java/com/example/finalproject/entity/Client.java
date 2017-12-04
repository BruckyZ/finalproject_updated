package com.example.finalproject.entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
public class Client {

    @Id
    @GeneratedValue
    private long id;

    @NotNull
    @Size(min = 3)
    private String firstName;
    @NotNull
    @Size(min = 3)
    private String lastName;
    @NotNull
    @Size(min = 3)
    private String contactNumber;
    @NotNull
    @Size(min = 3)
    private String email;
    @NotNull
    @Size(min = 3)
    private String gender;

    public Client() {
    }

    public Client(String firstName, String lastName, String contactNumber, String email, String gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.contactNumber = contactNumber;
        this.email = email;
        this.gender = gender;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
