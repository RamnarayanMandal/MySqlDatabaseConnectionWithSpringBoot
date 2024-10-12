package com.example.demo.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Model.Product;
import com.example.demo.Repository.ProductRepo;

@Service
public class ProductService {
    
    @Autowired
    ProductRepo productRepo;

    // Save the product and return the updated list of products
    public List<Product> addProduct(Product product) {
        // Save the product
        productRepo.save(product);
        
        // Return the list of all products
        return productRepo.findAll();
    }
}
