package com.felipemarcel.store.service;

import com.felipemarcel.store.exception.ResourceNotFoundException;
import com.felipemarcel.store.model.Order;
import com.felipemarcel.store.model.OrderProduct;
import com.felipemarcel.store.model.Product;
import com.felipemarcel.store.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.util.List;

import static com.felipemarcel.store.model.OrderStatus.PAID;
import static java.time.LocalDate.now;

@Service
@Transactional
public class OrderService {

    @Autowired
    private OrderRepository repository;

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderProductService orderProductService;

    public Page<Order> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public void remove(Long id) {
        repository.deleteById(id);
    }

    public Order save(Order order) {
        order.setDateCreated(now());
        return repository.save(order);
    }

    public Order findBy(Long id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Order not found."));
    }

    public void addProduct(Long id, Product product, Integer quantity) {
        Order order = findBy(id);
        Product recoveredProduct = productService.findBy(product.getId());
        orderProductService.save(new OrderProduct(order, product, quantity));
    }

    public void setPaid(Long id) {
        Order order = findBy(id);
        order.setStatus(PAID.name());
        repository.save(order);
    }
}
