package com.example.demo.Repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.Model.Cart;

public interface CartRepo extends JpaRepository<Cart, Integer> {
    List<Cart> findAllByUserId(int userId); // Add this method
}
