package com.example.demo.Service;

import java.util.List;
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
}
