package com.example.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Model.Product;
import com.example.demo.Service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {
    
    @Autowired
    ProductService productService;
    
    @PostMapping("/")
    public ResponseEntity<?> addProduct(@RequestBody Product product) {
        try {
            List<Product> allProducts = productService.addProduct(product);
            
            if (allProducts == null || allProducts.isEmpty()) {
                return new ResponseEntity<>("Error: Product could not be added", HttpStatus.SERVICE_UNAVAILABLE);
            }
            
            // Returning the newly added product and status code 201
            return new ResponseEntity<>(product, HttpStatus.CREATED);
            
        } catch (RuntimeException e) {
            return new ResponseEntity<>("Runtime Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("An unexpected error occurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
