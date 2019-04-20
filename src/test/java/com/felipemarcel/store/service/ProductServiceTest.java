package com.felipemarcel.store.service;

import com.felipemarcel.store.model.Product;
import com.felipemarcel.store.repository.ProductRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;

@RunWith(SpringRunner.class)
public class ProductServiceTest {

    @Autowired
    private ProductService service;

    @MockBean
    private ProductRepository repository;

    @TestConfiguration
    static class ProductServiceTestContextConfiguration {

        @Bean
        public ProductService productService() {
            return new ProductService();
        }
    }

    @Test
    public void contextLoads() {
        Assertions.assertThat(service).isNotNull();
    }

    @Test
    public void shouldReturnAllCustomers() {
        Page<Product> products = service.findAll(PageRequest.of(0, 5));
        if(products != null){
            assertThat(products.getContent(), hasSize(0));
        }
    }
}
