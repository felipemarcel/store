package com.felipemarcel.store.service;

import com.felipemarcel.store.exception.ResourceNotFoundException;
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

    public void remove(Long id) {
        repository.deleteById(id);
    }

    public Order save(Order order){
        return repository.save(order);
    }

    public Order findBy(Long id){
        return repository.findById(id).orElseThrow(ResourceNotFoundException::new);
    }
}
