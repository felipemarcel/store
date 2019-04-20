package com.felipemarcel.store.service;

import com.felipemarcel.store.model.Order;
import com.felipemarcel.store.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private OrderRepository repository;

    public Page<Order> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }
}
