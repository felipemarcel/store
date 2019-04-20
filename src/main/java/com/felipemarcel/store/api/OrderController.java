package com.felipemarcel.store.api;

import com.felipemarcel.store.model.Order;
import com.felipemarcel.store.model.Product;
import com.felipemarcel.store.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.net.URI;
import java.net.URISyntaxException;

import static org.springframework.http.ResponseEntity.created;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService service;

    @GetMapping
    @ResponseBody
    public ResponseEntity<?> listAll(@RequestParam(value = "page", defaultValue = "0") int page,
                                     @RequestParam(value = "size", defaultValue = "5") int size) {
        return ok(service.findAll(PageRequest.of(page, size)));
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<Order> save(@Valid @RequestBody Order order) throws URISyntaxException {
        Order saved = service.save(order);
        return created(new URI(saved.getId().toString())).build();
    }

    @ResponseBody
    @GetMapping("/{id}")
    public ResponseEntity<Order> findBy(@PathVariable("id") Long id) {
        return ok(service.findBy(id));
    }

    @ResponseBody
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        service.remove(id);
        return ok().build();
    }

    @ResponseBody
    @PostMapping("/{id}/products")
    public ResponseEntity<?> addProduct(@PathVariable("id") Long id,
                                        @Valid @RequestBody Product product,
                                        @RequestBody Integer quantity) {
        service.addProduct(id, product, quantity);
        return ok().build();
    }

    @ResponseBody
    @PutMapping("/{id}")
    public ResponseEntity<?> setPaid(@PathVariable("id") Long id) {
        service.setPaid(id);
        return ok().build();
    }
}
