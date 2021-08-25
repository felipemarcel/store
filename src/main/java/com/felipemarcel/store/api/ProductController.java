package com.felipemarcel.store.api;

import com.felipemarcel.store.model.Product;
import com.felipemarcel.store.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.http.ResponseEntity.created;

@Api(
        value = "Produtos",
        consumes = "application/json",
        produces = "application/json",
        tags = "Store - Produtos"
)
@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService service;

    @Autowired
    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping
    @ResponseBody
    @ApiOperation(value = "Responsável por listar todos os produtos.")
    public ResponseEntity<?> listAll(@RequestParam(value = "page", defaultValue = "0") int page,
                                     @RequestParam(value = "size", defaultValue = "5") int size) {
        return ok(service.findAll(PageRequest.of(page, size)));
    }

    @PostMapping
    @ResponseBody
    @ApiOperation(value = "Responsável por adicionar um produto.")
    public ResponseEntity<?> save(@Valid @RequestBody Product product) throws URISyntaxException {
        Product saved = service.save(product);
        return created(new URI(saved.getId().toString())).build();
    }

    @ResponseBody
    @GetMapping("/{id}")
    @ApiOperation(value = "Responsável por buscar um produto pelo seu identificador.")
    public ResponseEntity<Product> findBy(@PathVariable("id") Long id) {
        return ok(service.findBy(id));
    }

    @ResponseBody
    @PutMapping
    @ApiOperation(value = "Responsável por atualizar informações de um produto.")
    public ResponseEntity<?> update(@Valid @RequestBody Product product) {
        return ok().build();
    }

    @ResponseBody
    @DeleteMapping("/{id}")
    @ApiOperation(value = "Responsável por removr um produto pelo seu identificador.")
    public ResponseEntity<?> remove(@PathVariable("id") Long id){
        service.remove(id);
        return ok().build();
    }
}
