package com.felipemarcel.store.service;

import com.felipemarcel.store.model.Customer;
import com.felipemarcel.store.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository repository;
    public Page<Customer> findAll(Pageable pageable){
        return repository.findAll(pageable);
    }

}
