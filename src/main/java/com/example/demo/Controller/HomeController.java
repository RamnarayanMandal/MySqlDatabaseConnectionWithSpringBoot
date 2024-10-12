package com.example.demo.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // Use RestController for REST APIs
public class HomeController {

    @GetMapping("/")
    public String greet() {
        System.out.println("Hi Ramnarayan Mandal");
        return "Welcome to my webpage"; // This will be returned as plain text
    }
}
