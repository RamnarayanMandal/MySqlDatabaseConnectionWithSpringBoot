package com.example.demo.Model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class ProductOrder {

    // Default constructor
    public ProductOrder() {
        super();
    }

    // Parameterized constructor
    public ProductOrder(int id, List<Product> products, int productQuantity, Address address, User user, String status,
                        String paymentMode, boolean payStatus) {
        this.id = id;
        this.products = products;
        this.productQuantity = productQuantity;
        this.address = address;
        this.user = user;
        this.status = status;
        this.paymentMode = paymentMode;
        this.payStatus = payStatus;
    }

    // Unique identifier for each order
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    // List of products associated with the order
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    private List<Product> products;

    // Quantity of the products in this order
    private int productQuantity;

    // Delivery address for the order
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;

    // The user who placed the order
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // Status of the order (e.g., "Pending", "Shipped", "Delivered")
    @Column(nullable = false)
    private String status;

    // Mode of payment (e.g., "Credit Card", "Cash on Delivery", "UPI")
    @Column(nullable = false)
    private String paymentMode;

    // Payment status (true = paid, false = not paid)
    @Column(nullable = false)
    private boolean payStatus;

    // Getters and Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

    public boolean isPayStatus() {
        return payStatus;
    }

    public void setPayStatus(boolean payStatus) {
        this.payStatus = payStatus;
    }

    @Override
    public String toString() {
        return "ProductOrder{" +
                "id=" + id +
                ", products=" + products +
                ", productQuantity=" + productQuantity +
                ", address=" + address +
                ", user=" + user +
                ", status='" + status + '\'' +
                ", paymentMode='" + paymentMode + '\'' +
                ", payStatus=" + payStatus +
                '}';
    }
}
