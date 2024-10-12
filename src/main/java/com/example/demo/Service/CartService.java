package com.example.demo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Repository.CartRepo;

@Service
public class CartService {
	@Autowired
	CartRepo cartRepo;

}
