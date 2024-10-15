package com.example.demo.Controller;

import java.util.List;

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

import com.example.demo.Model.Address;
import com.example.demo.Service.AddressService;

@RestController
@RequestMapping("/address")
public class AddressController {
	
	@Autowired
	AddressService addressService;  // Corrected spelling here
	
	@PostMapping("/")
	public ResponseEntity<?> addAddress(@RequestBody Address address){ 
		try {
			Address newAddress = addressService.addAddress(address); 
			
			if(address==null) {
				return new ResponseEntity<>("Something went wrong while creating the Address",HttpStatus.SERVICE_UNAVAILABLE);
				
			}
			return new ResponseEntity<>(newAddress,HttpStatus.OK);  
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			
			return new ResponseEntity<>("occur error while adding the Adress"+ e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/userId/{userId}")
  public ResponseEntity<?> getAllAddressbyUserId(@PathVariable int userId){
		try {
		List<Address> address = addressService.getAddressbyUserId(userId);
		
		if(address.isEmpty()) {
			return new ResponseEntity<>("Something went wrong while fetching Address",HttpStatus.SERVICE_UNAVAILABLE);
			
		}
		
		return new ResponseEntity<>(address,HttpStatus.OK);
			
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>("Occur error while fetching Adress"+ e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getAddressbyId(@PathVariable int id){
		try {
			Address address = addressService.getAddressbyId(id);
			
			if(address==null) {
				return new ResponseEntity<>("Somthing went wrong while fetching Address",HttpStatus.SERVICE_UNAVAILABLE);
			}
			
			return new ResponseEntity<>(address,HttpStatus.OK);
			
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>("occur error while fetching Address:"+e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@PutMapping("/{id}")
	public ResponseEntity<?> UpdateAddress(@PathVariable int id,@RequestBody Address address){
		try {
			 Address Updateaddress= addressService.updateAddress(id,address);
			 
			 if(Updateaddress==null) {
				 return new ResponseEntity<>("Something went wrong while updating Address",HttpStatus.SERVICE_UNAVAILABLE);
			 }
			 return new ResponseEntity<>(address,HttpStatus.OK);
			
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>("occur error while Updating Address:"+e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> removeAddress(@PathVariable int id){
		try {
		   Address address = addressService.removeAddress(id);
		   
		   if(address==null) {
			   return new ResponseEntity<>("Somthing went wrong while deleting Address",HttpStatus.SERVICE_UNAVAILABLE);
			   
		   }
		   
		   return new ResponseEntity<>(address,HttpStatus.OK);
			
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>("Occur error while removing Address:" +e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
