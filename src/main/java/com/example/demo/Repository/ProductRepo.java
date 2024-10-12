package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.example.demo.Model.Product;

@Repository
@Component
public interface ProductRepo extends JpaRepository<Product, Integer> {

}
