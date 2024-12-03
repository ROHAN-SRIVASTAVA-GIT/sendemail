package com.example.sendmail.repo;

import org.springframework.data.repository.CrudRepository;
import com.example.sendmail.model.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
}
