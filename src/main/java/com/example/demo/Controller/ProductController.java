package com.example.demo.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
    
   @GetMapping("/") 
   public ResponseEntity<?> getAllProduct(){
	   try {
		   
		  List<Product> products = productService.getAllProduct();
		  
		  if(products.isEmpty()) {
			  return new ResponseEntity<>("Something went wrong wile the fetching the product", HttpStatus.SERVICE_UNAVAILABLE);
		  }
		  
		  return new ResponseEntity<>(products,HttpStatus.OK);
		
	} catch (RuntimeException e) {
        return new ResponseEntity<>("Runtime Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    } catch (Exception e) {
        e.printStackTrace();
        return new ResponseEntity<>("An unexpected error occurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
	   
   }
   
   @GetMapping("/{productId}")
   public ResponseEntity<?> GetProductById(@PathVariable int productId){
	   try {
		    Optional<Product>  product = Optional.ofNullable(productService.getProductbyId(productId));
		    
		    if(product==null) {
		    	return new ResponseEntity<>("Something went wrong while fetching the product by this"+productId , HttpStatus.SERVICE_UNAVAILABLE);
		    }
		    
		    return new ResponseEntity<>(product,HttpStatus.OK);
		   
		
	} catch (RuntimeException e) {
        return new ResponseEntity<>("Runtime Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    } catch (Exception e) {
        e.printStackTrace();
        return new ResponseEntity<>("An unexpected error occurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
   }
   
   @PutMapping("/{productId}")
   public ResponseEntity<?> updateProductById(@PathVariable int productId,@RequestBody Product product){
	   try {
		  Product Updateproduct = productService.UpdateById(productId,product);
		  
		  if(Updateproduct==null) {
			  return new ResponseEntity<>("something is wrong while fetching the product",HttpStatus.NOT_FOUND);
		  }
		  
		  return new ResponseEntity<>(Updateproduct,HttpStatus.OK);
		
	}catch(RuntimeException e) {
		e.printStackTrace();
		
		return new ResponseEntity<>("Runtime Error:" +e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR); 
		
	}
	   catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
		return new ResponseEntity<>("An unexpected error occurred:"+e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
	}
   }
   
   @DeleteMapping("/{productId}")
   public ResponseEntity<?> removeProduct(@PathVariable int productId) {
       try {
           Product product = productService.removeProduct(productId);

           if (product == null) {
               return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);
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
