package com.example.demo.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Model.Address;

@Repository
public interface AddressRepo extends JpaRepository<Address, Integer> {

	List<Address> findAddressByUserId(int userId);

}
