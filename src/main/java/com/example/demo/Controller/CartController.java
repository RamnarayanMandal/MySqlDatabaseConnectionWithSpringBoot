package com.example.demo.Controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.Model.Cart;
import com.example.demo.Model.CartItems;
import com.example.demo.Service.CartService;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    CartService cartService;

    @PostMapping("/")
    public ResponseEntity<?> addProductIntoCart(@RequestBody Cart cart) {
        try {
            if (cart.getCartItems() != null) {
                for (CartItems cartItem : cart.getCartItems()) {
                    cartItem.setCart(cart); // Associate this cart item with the current cart
                }
            }

            Cart newCart = cartService.addProductIntoCart(cart);
            if (newCart == null) {
                return new ResponseEntity<>("Failed to add the product to the cart", HttpStatus.INTERNAL_SERVER_ERROR);
            }

            return new ResponseEntity<>(newCart, HttpStatus.CREATED); // Use CREATED (201) for new resources
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred while processing the request: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getAllCartByUserId(@PathVariable int userId) {
        try {
            List<Cart> carts = cartService.getAllCartByUserId(userId);
            if (carts.isEmpty()) {
                return new ResponseEntity<>("There are no carts for user ID: " + userId, HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(carts, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred while processing the request: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
