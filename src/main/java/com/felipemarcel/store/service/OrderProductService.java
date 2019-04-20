package com.felipemarcel.store.service;

import com.felipemarcel.store.model.OrderProduct;
import com.felipemarcel.store.repository.OrderProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class OrderProductService {

    @Autowired
    private OrderProductRepository repository;

    public OrderProduct save(OrderProduct orderProduct) {
        return repository.save(orderProduct);
    }
}
