package com.felipemarcel.store.api;

import com.felipemarcel.store.model.Order;
import com.felipemarcel.store.model.Product;
import com.felipemarcel.store.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.net.URI;
import java.net.URISyntaxException;

import static org.springframework.http.ResponseEntity.created;
import static org.springframework.http.ResponseEntity.ok;

@Api(
        value = "Pedido",
        consumes = "application/json",
        produces = "application/json",
        tags = "Store - Pedidos"
)
@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService service;

    @Autowired
    public OrderController(OrderService service) {
        this.service = service;
    }

    @GetMapping
    @ResponseBody
    @ApiOperation(value = "Responsável por listar todos os pedidos.")
    public ResponseEntity<?> listAll(@RequestParam(value = "page", defaultValue = "0") int page,
                                     @RequestParam(value = "size", defaultValue = "5") int size) {
        return ok(service.findAll(PageRequest.of(page, size)));
    }

    @PostMapping
    @ResponseBody
    @ApiOperation(value = "Responsável por cadastrar um pedido.")
    public ResponseEntity<Order> save(@Valid @RequestBody Order order) throws URISyntaxException {
        Order saved = service.save(order);
        return created(new URI(saved.getId().toString())).build();
    }

    @ResponseBody
    @GetMapping("/{id}")
    @ApiOperation(value = "Responsável por buscar um pedido pelo seu identificador.")
    public ResponseEntity<Order> findBy(@PathVariable("id") Long id) {
        return ok(service.findBy(id));
    }

    @ResponseBody
    @DeleteMapping("/{id}")
    @ApiOperation(value = "Responsável por remover um pedido a partir do seu identificador.")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        service.remove(id);
        return ok().build();
    }

    @ResponseBody
    @PutMapping("/{id}/products")
    @ApiOperation(value = "Responsável por adicionar um produto em um pedido.")
    public ResponseEntity<?> addProduct(@PathVariable("id") Long id,
                                        @Valid @RequestBody Product product) {
        service.addProduct(id, product, 1);
        return ok().build();
    }

    @ResponseBody
    @PutMapping("/{id}")
    @ApiOperation(value = "Responsável por definir um pedido como pago.")
    public ResponseEntity<?> setPaid(@PathVariable("id") Long id) {
        service.setPaid(id);
        return ok().build();
    }
}
