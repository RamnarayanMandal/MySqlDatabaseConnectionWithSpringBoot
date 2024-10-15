package com.example.demo.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String street;
    private String city;
    private String state;
    private String pinCode;

    // Reference to User
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")  // This will create a foreign key in the Address table
    private User user;

    public Address() {}

    public Address(int id, String street, String city, String state, String pinCode, User user) {
        this.id = id;
        this.street = street;
        this.city = city;
        this.state = state;
        this.pinCode = pinCode;
        this.user = user;  // Set user in the constructor
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPinCode() {
        return pinCode;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }

    public User getUser() {
        return user;  // Getter for user
    }

    public void setUser(User user) {
        this.user = user;  // Setter for user
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", pinCode='" + pinCode + '\'' +
                ", user=" + user.getName() +  // To display the user's name associated with the address
                '}';
    }
}
