package com.felipemarcel.store.api;

import com.felipemarcel.store.model.Customer;
import com.felipemarcel.store.service.CustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.net.URI;
import java.net.URISyntaxException;

import static org.springframework.http.ResponseEntity.created;
import static org.springframework.http.ResponseEntity.ok;

@Api(
        value = "Clientes",
        consumes = "application/json",
        produces = "application/json",
        tags = "Store - Clientes"
)
@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService service;

    @Autowired
    public CustomerController(CustomerService service) {
        this.service = service;
    }

    @ResponseBody
    @GetMapping
    @ApiOperation(value = "Responsável por listar todos os clientes")
    public ResponseEntity<?> listAll(@RequestParam(value = "page", defaultValue = "0") int page,
                                     @RequestParam(value = "size", defaultValue = "5") int size) {
        return ok(service.findAll(PageRequest.of(page, size)));
    }

    @ResponseBody
    @GetMapping("/{id}")
    @ApiOperation(value = "Responsável por buscar cliente por identificador.")
    public ResponseEntity<Customer> findBy(@PathVariable("id") Long id) {
        return ok(service.findBy(id));
    }

    @PostMapping
    @ResponseBody
    @ApiOperation(value = "Responsável por cadastrar cliente.")
    public ResponseEntity<?> save(@Valid @RequestBody Customer customer) throws URISyntaxException {
        return created(new URI(service.save(customer).getId().toString())).build();
    }


}
