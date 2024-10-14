package com.example.demo.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Model.Product;
import com.example.demo.Model.User;
import com.example.demo.Repository.ProductRepo;

@Service
public class ProductService {
    
    private static final Product UpdateById = null;
	@Autowired
    ProductRepo productRepo;

    // Save the product and return the updated list of products
    public List<Product> addProduct(Product product) {
       
        // Return the list of all products
        return (List<Product>) productRepo.save(product);
    }

	public List<Product> getAllProduct() {
		// TODO Auto-generated method stub
		return productRepo.findAll();
	}

	public Product getProductbyId(int productId) {
		// TODO Auto-generated method stub
		return productRepo.findById(productId).orElse(null);
	}

	public Product UpdateById(int productId, Product product) {
	
	    // Find the existing product by productId
	    Optional<Product> existingProductOptional = productRepo.findById(productId);
	  

	    // If the product exists, update its fields
	    if (existingProductOptional.isPresent()) {
	        Product existingProduct = existingProductOptional.get();

	        // Update the existing product with the new details from the input 'product'
	        existingProduct.setName(product.getName());
	        existingProduct.setDescription(product.getDescription());
	        existingProduct.setPrice(product.getPrice());
	        existingProduct.setQuantity(product.getQuantity());
	        existingProduct.setSku(product.getSku());
	        existingProduct.setImages(product.getImages());
	        existingProduct.setThumbnailImage(product.getThumbnailImage());

	        // Save the updated product back to the repository
	        return productRepo.save(existingProduct);
	    } else {
	        // Handle the case where the product is not found
	        throw new RuntimeException("Product not found with id: " + productId);
	    }
	}

	public Product removeProduct(int productId) {
	    // Retrieve the product before deleting it
	    Optional<Product> productOpt = productRepo.findById(productId);
	    
	    if (productOpt.isPresent()) {
	        productRepo.deleteById(productId); // Delete the product
	        return productOpt.get(); // Return the deleted product
	    } else {
	        return null; // Product doesn't exist
	    }
	}

  
	

}
