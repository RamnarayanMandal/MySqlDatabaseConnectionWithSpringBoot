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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Model.User;
import com.example.demo.Service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

    private static final String Httpstatus = null;
	private static final int ResponseEntity = 0;
	@Autowired
    private UserService userService;

    @GetMapping("/")
    public ResponseEntity<?> getUsers() {
        List<User> users = userService.getAllUsers();
        if (users.isEmpty()) {
            return new ResponseEntity<>("No users found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
    
    @PostMapping("/")
    public ResponseEntity<?> createUser(@RequestBody User user) {
        try {
            User newUser = userService.createUser(user);
            if (newUser == null) {
                return new ResponseEntity<>("Something went wrong while creating user", HttpStatus.SERVICE_UNAVAILABLE);
            }
            return new ResponseEntity<>("User created successfully: " + newUser, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error occurred while creating user: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }  
    }
    
    @GetMapping("/{id}")
    	public ResponseEntity<?> getUserById(@PathVariable int id){
    		try {
    		   User user = userService.getUserById(id);
    		   if(user == null) {
    			   return new ResponseEntity<>("Something went wrong while fetch user data",HttpStatus.SERVICE_UNAVAILABLE);
    			   
    		   }
    		   
    		   return new ResponseEntity<>("User fetch successfully:" +user, HttpStatus.OK);
				
			} catch (Exception e) {
				e.printStackTrace();
				
				return new ResponseEntity<>("Error occurred while fetching the user: " +e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
				
			}
    	}
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable int id) {
        try {
            User deletedUser = userService.deleteUser(id);
            
            // Check if the user was actually found and deleted
            if (deletedUser == null) {
                return new ResponseEntity<>("User not found or already deleted.", HttpStatus.NOT_FOUND);
            }
            
            // Successfully deleted the user, return the deleted user details
            return new ResponseEntity<>(deletedUser, HttpStatus.OK);
            
        } catch (RuntimeException e) {
            // Handling specific user not found exception if needed
            return new ResponseEntity<>("User not found: " + e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            // Log and return generic server error for any other issues
            e.printStackTrace();
            return new ResponseEntity<>("Error occurred while deleting the user: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> updateUserById(@PathVariable int id, @RequestBody User user) {
        try {
            // Attempt to update the user
            User updatedUser = userService.updateUser(id, user);
            
            // Successfully updated the user, return the updated user details
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
            
        } catch (RuntimeException e) {
            // Handling specific user not found exception
            return new ResponseEntity<>("User not found: " + e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            // Log and return generic server error for any other issues
            e.printStackTrace();
            return new ResponseEntity<>("Error occurred while updating the user: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
