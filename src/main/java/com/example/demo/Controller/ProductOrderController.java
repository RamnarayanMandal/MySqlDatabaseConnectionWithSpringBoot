package com.example.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Model.ProductOrder;
import com.example.demo.Service.ProductOrderService;

@RestController
@RequestMapping("/productOrder")
public class ProductOrderController {
	
	@Autowired
	ProductOrderService service;
	
	
	@PostMapping("/")
	public ResponseEntity<?> createOrder(@RequestBody ProductOrder productOrder){
		 try {
			 
			    ProductOrder order =  service.createOrder(productOrder);
			    
			    if(order==null) {
			    	return new ResponseEntity<>("Something is wrong while create the product Order",HttpStatus.NOT_FOUND);
			    }
			    
			    return new ResponseEntity<>(order,HttpStatus.OK);
			
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>("An error is occur while creating Order"+ e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getProductOrderById(@PathVariable int id) {
	    try {
	        // Fetch the product order by ID
	    	System.out.println(id+"....................................................");
	        ProductOrder order = service.getProductOrderById(id);
	        
	        // Check if the order is null
	        if (order == null) {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                                 .body("Order not found for the given ID: " + id);
	        }
	        
	        // Return the found order
	        return ResponseEntity.ok(order);
	        
	    } catch (Exception e) {
	        // Log the exception for debugging (optional)
	        e.printStackTrace();
	        
	        // Return an internal server error response
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                             .body("An error occurred while retrieving the order: " + e.getMessage());
	    }
	}

	
	

}
