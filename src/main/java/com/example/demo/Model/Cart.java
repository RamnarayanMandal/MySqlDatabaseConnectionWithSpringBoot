package com.example.demo.Model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    // Many carts can be linked to one user
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // One cart can have multiple cart items
    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CartItems> cartItems;

    // Many carts can be linked to one address
    @ManyToOne
    @JoinColumn(name = "address_id", nullable = false)
    private Address address;

    public Cart() {
        super();
    }

    public Cart(int id, User user, List<CartItems> cartItems, Address address) {
        super();
        this.id = id;
        this.user = user;
        this.cartItems = cartItems;
        this.address = address;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<CartItems> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItems> cartItems) {
        this.cartItems = cartItems;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Cart {id=" + id + ", user=" + user + ", cartItems=" + cartItems + ", address=" + address + "}";
    }
}
