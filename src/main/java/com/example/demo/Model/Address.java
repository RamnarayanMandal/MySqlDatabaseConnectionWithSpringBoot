package com.example.demo.Model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String street;
    private String city;
    private String state;

    // One address can be associated with multiple carts
    @OneToMany(mappedBy = "address")
    private List<Cart> carts;

    // Constructors, Getters and Setters
    public Address() {}

    public Address(int id, String street, String city, String state) {
        this.id = id;
        this.street = street;
        this.city = city;
        this.state = state;
    }

    // Getters and setters

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

    public List<Cart> getCarts() {
        return carts;
    }

    public void setCarts(List<Cart> carts) {
        this.carts = carts;
    }
}
