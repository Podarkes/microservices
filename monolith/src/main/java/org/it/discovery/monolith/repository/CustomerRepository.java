package org.it.discovery.monolith.repository;

import java.util.List;

import org.it.discovery.monolith.domain.Customer;

public interface CustomerRepository {

	Customer findById(int customerId);

	void save(Customer customer);

	List<Customer> findAll();

}
