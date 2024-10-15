package com.example.demo.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.Model.Product;
import com.example.demo.Service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    // Add a new product
    @PostMapping("/")
    public ResponseEntity<?> addProduct(@RequestBody Product product) {
        try {
            Product addedProduct = productService.addProduct(product); // Fix the return type to Product
            
            if (addedProduct == null) {
                return new ResponseEntity<>("Error: Product could not be added", HttpStatus.SERVICE_UNAVAILABLE);
            }

            // Return the added product and status code 201
            return new ResponseEntity<>(addedProduct, HttpStatus.CREATED);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("An unexpected error occurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get all products
    @GetMapping("/")
    public ResponseEntity<?> getAllProducts() {
        try {
            List<Product> products = productService.getAllProduct(); // Ensure this returns List<Product>

            if (products.isEmpty()) {
                return new ResponseEntity<>("No products found", HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(products, HttpStatus.OK);

        } catch (RuntimeException e) {
            return new ResponseEntity<>("Runtime Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("An unexpected error occurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get product by ID
    @GetMapping("/{productId}")
    public ResponseEntity<?> getProductById(@PathVariable int productId) {
        try {
            Product product = productService.getProductbyId(productId);

            if (product==null) {
                return new ResponseEntity<>(product.getId(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Product with ID " + productId + " not found", HttpStatus.NOT_FOUND);
            }

        } catch (RuntimeException e) {
            return new ResponseEntity<>("Runtime Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("An unexpected error occurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Update product by ID
    @PutMapping("/{productId}")
    public ResponseEntity<?> updateProductById(@PathVariable int productId, @RequestBody Product product) {
        try {
            Product updatedProduct = productService.UpdateById(productId, product);

            if (updatedProduct == null) {
                return new ResponseEntity<>("Product with ID " + productId + " not found", HttpStatus.NOT_FOUND);
            }

            return new ResponseEntity<>(updatedProduct, HttpStatus.OK);

        } catch (RuntimeException e) {
            e.printStackTrace();
            return new ResponseEntity<>("Runtime Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("An unexpected error occurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Delete product by ID
    @DeleteMapping("/{productId}")
    public ResponseEntity<?> removeProduct(@PathVariable int productId) {
        try {
            Product product = productService.removeProduct(productId);

            if (product == null) {
                return new ResponseEntity<>("Product with ID " + productId + " not found", HttpStatus.NOT_FOUND);
            }

            return new ResponseEntity<>(product, HttpStatus.OK);

        } catch (RuntimeException e) {
            e.printStackTrace();
            return new ResponseEntity<>("Runtime Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("An unexpected error occurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
