package com.example.demo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.Model.Address;
import com.example.demo.Model.ProductOrder;
import com.example.demo.Repository.AddressRepo;
import com.example.demo.Repository.ProductOrderRepo;
import com.example.demo.Repository.ProductRepo;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@Service
public class ProductOrderService {
    @Autowired
    private ProductOrderRepo orderRepo;

    @Autowired
    private AddressRepo addressRepo;

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private EntityManager entityManager;

    @Transactional
    public ProductOrder createOrder(ProductOrder productOrder) {
        // Check if an order already exists for the given user and address
        ProductOrder existingOrder = orderRepo.findByUserIdAndAddressId(
            productOrder.getUser().getId(),
            productOrder.getAddress().getId()
        );

        if (existingOrder != null) {
            // If the order exists, return it or handle it as needed
            return existingOrder; // Optionally throw an exception or update the order
        }

        // Handle address retrieval
        if (productOrder.getAddress() != null && productOrder.getAddress().getId() != 0) {
            Address managedAddress = entityManager.find(Address.class, productOrder.getAddress().getId());
            productOrder.setAddress(managedAddress);
        }

        return orderRepo.save(productOrder);
    }

	public ProductOrder getProductOrderById(int id) {
		// TODO Auto-generated method stub
		return orderRepo.getById(id);
	}
}
