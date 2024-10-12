package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.example.demo.Model.Cart;

@Repository
@Component
public interface CartRepo extends JpaRepository<Cart, Integer> {

}
