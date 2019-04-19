package com.felipemarcel.store.api;

import com.felipemarcel.store.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.ResponseEntity.ok;

@RestController(value = "/customers")
public class CustomerController {

    @Autowired
    private CustomerService service;

    @ResponseBody
    @GetMapping(params = {"page", "size"})
    public ResponseEntity<?> listAll(@RequestParam("page") int page, @RequestParam("size") int size) {
        return ok(service.findAll(PageRequest.of(page, size)));
    }
}
