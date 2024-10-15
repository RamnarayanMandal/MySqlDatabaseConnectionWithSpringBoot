package com.example.demo.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.Model.Cart;
import com.example.demo.Repository.CartRepo;

@Service
public class CartService {
    @Autowired
    CartRepo cartRepo;

    public Cart addProductIntoCart(Cart cart) {
        return cartRepo.save(cart);
    }

    public List<Cart> getAllCartByUserId(int userId) {
        return cartRepo.findAllByUserId(userId); // Change made here
    }

    public Cart removeCartById(int id) throws Exception {
        Optional<Cart> optionalCart = cartRepo.findById(id); // Fetch cart by ID

        if (optionalCart.isPresent()) {
            Cart cartToRemove = optionalCart.get();
            cartRepo.deleteById(id); // Delete cart from the repository
            return cartToRemove; // Return the deleted cart
        } else {
            throw new Exception("Cart with ID " + id + " not found."); // Custom exception if not found
        }
    }

    public List<Cart> removeCartByUserId(int userId) {
        List<Cart> deletedCarts = new ArrayList<>(); // List to store deleted carts
        List<Cart> userCarts = cartRepo.findAllByUserId(userId); // Assuming you have a method to find carts by user ID

        for (Cart cart : userCarts) {
            cartRepo.delete(cart); // Delete each cart
            deletedCarts.add(cart); // Add to the list of deleted carts
        }

        return deletedCarts; // Return the list of deleted carts
    }
}
