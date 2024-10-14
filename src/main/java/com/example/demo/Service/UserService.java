package com.example.demo.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Model.User;
import com.example.demo.Repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userResp;

    // Fetch all users
    public List<User> getAllUsers() {
        return userResp.findAll();
    }

    // Create a new user
    public User createUser(User user) {
        return userResp.save(user); // Save the user and return the saved entity
    }

	public User getUserById(int id) {
		
		return userResp.getById(id);
	}

	public User deleteUser(int id) {
	    // First, fetch the user by ID
	    Optional<User> user = userResp.findById(id);

	    // Check if the user exists
	    if (user.isPresent()) {
	        // Delete the user if found
	        userResp.deleteById(id);
	        // Return the deleted user details
	        return user.get();
	    } else {
	        // Handle the case where the user is not found
	        throw new RuntimeException("User not found with id: " + id);
	    }
	}

	public User updateUser(int id, User updatedUser) {
	    // Retrieve the user from the repository by ID
	    Optional<User> existingUserOptional = userResp.findById(id);

	    // Check if the user exists
	    if (existingUserOptional.isPresent()) {
	        // Get the existing user
	        User existingUser = existingUserOptional.get();

	        // Update fields in the existing user with the new user data
	        existingUser.setName(updatedUser.getName());
	        existingUser.setEmail(updatedUser.getEmail());
	        existingUser.setPhoneNo(updatedUser.getPhoneNo());
	        existingUser.setPassword(updatedUser.getPassword());
	        
	        
  	        return userResp.save(existingUser);
  	        
	    } else {                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              
	        // Handle the case where the user is not found
	        throw new RuntimeException("User not found with id: " + id);
	    }
	}

}


