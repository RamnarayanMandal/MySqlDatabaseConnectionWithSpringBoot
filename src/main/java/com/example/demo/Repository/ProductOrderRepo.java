package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Model.ProductOrder;

@Repository
public interface ProductOrderRepo extends JpaRepository<ProductOrder, Integer> {

	ProductOrder findByUserIdAndAddressId(int id, int id2);

}
