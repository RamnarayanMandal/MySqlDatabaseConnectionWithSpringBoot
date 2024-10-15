package com.example.demo.Service;

import java.util.List;
import java.util.Optional;

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



public List<Address> getAddressbyUserId(int userId) {
	// TODO Auto-generated method stub
	 return addressRepo.findAddressByUserId(userId);
};

public Address getAddressbyId(int id) {
	return addressRepo.findById(id).orElse(null);
}



public Address updateAddress(int id, Address address) {
    Optional<Address> optionalAddress = addressRepo.findById(id);

    // Check if the address exists in the database
    if (optionalAddress.isPresent()) {
        Address existingAddress = optionalAddress.get();
        existingAddress.setCity(address.getCity());
        existingAddress.setPinCode(address.getPinCode());
        existingAddress.setState(address.getState());
        existingAddress.setStreet(address.getStreet());

        // Save the updated address
        return addressRepo.save(existingAddress);
    } else {
        // Handle case when the address is not found (optional)
        // You can throw an exception or return null based on your use case
        return null;
    }
}


public Address removeAddress(int id) throws Exception {
    Optional<Address> optionalAddress = addressRepo.findById(id);

    if (optionalAddress.isPresent()) {
        Address addressToRemove = optionalAddress.get();
        addressRepo.deleteById(id);
        return addressToRemove;
    } else {
        throw new Exception("Address with ID " + id + " not found.");
    }
}

}
