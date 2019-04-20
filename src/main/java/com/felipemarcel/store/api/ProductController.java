package com.felipemarcel.store.api;

import com.felipemarcel.store.model.Product;
import com.felipemarcel.store.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.http.ResponseEntity.created;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping
    @ResponseBody
    public ResponseEntity<?> listAll(@RequestParam(value = "page", defaultValue = "0") int page,
                                     @RequestParam(value = "size", defaultValue = "5") int size) {
        return ok(service.findAll(PageRequest.of(page, size)));
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<?> save(@Valid @RequestBody Product product) throws URISyntaxException {
        Product saved = service.save(product);
        return created(new URI(saved.getId().toString())).build();
    }

    @ResponseBody
    @GetMapping("/{id}")
    public ResponseEntity<Product> findBy(@PathVariable("id") Long id) {
        return ok(service.findBy(id));
    }

    @ResponseBody
    @PutMapping
    public ResponseEntity<?> update(@Valid @RequestBody Product product) {
        return ok().build();
    }

    @ResponseBody
    @DeleteMapping("/{id}")
    public ResponseEntity<?> remove(@PathVariable("id") Long id){
        service.remove(id);
        return ok().build();
    }
}
