package com.felipemarcel.store.service;

import com.felipemarcel.store.exception.ResourceNotFoundException;
import com.felipemarcel.store.model.Product;
import com.felipemarcel.store.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    public Page<Product> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Product save(Product product) {
        return repository.save(product);
    }

    public Product findBy(Long id) {
        return repository.findById(id).orElseThrow(ResourceNotFoundException::new);
    }

    public void remove(Long id) {
        repository.deleteById(id);
    }
}
