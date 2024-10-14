package com.example.demo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Model.Address;
import com.example.demo.Repository.AddressRepo;

@Service
public class AddressService {
   
  @Autowired	
  AddressRepo addressRepo;

public Address addAddress(Address address) {
	// TODO Auto-generated method stub
	return addressRepo.save(address);
}
  
}
