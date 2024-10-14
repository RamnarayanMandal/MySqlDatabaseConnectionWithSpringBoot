package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
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
			
			return new ResponseEntity<>("occur error while adding the Adress", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
