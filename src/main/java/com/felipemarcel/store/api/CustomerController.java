package com.felipemarcel.store.api;

import com.felipemarcel.store.model.Customer;
import com.felipemarcel.store.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService service;

    @ResponseBody
    @GetMapping
    public ResponseEntity<?> listAll(@RequestParam(value = "page", defaultValue = "0") int page,
                                     @RequestParam(value = "size", defaultValue = "5") int size) {
        return ok(service.findAll(PageRequest.of(page, size)));
    }

    @ResponseBody
    @GetMapping("/{id}")
    public ResponseEntity<Customer> findBy(@PathVariable("id") Long id) {
        return null;
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<?> save(@Valid @RequestBody Customer customer){
        return null;
    }


}
