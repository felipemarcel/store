package com.felipemarcel.store.service;

import com.felipemarcel.store.model.Product;
import com.felipemarcel.store.repository.ProductRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class ProductServiceTest {

    @MockBean
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
    public void shouldReturnAllProducts() {
        Page<Product> products = new PageImpl<>(Arrays.asList(
                new Product(1L, "Batata", 30.20, ""),
                new Product(2L, "Banana", 5.14, "")));

        when(service.findAll(PageRequest.of(0, 5))).thenReturn(products);

        Page<Product> recoveredProducts = service.findAll(PageRequest.of(0, 5));
        assertThat(recoveredProducts.getContent(), hasSize(2));
        verify(service, times(1)).findAll(PageRequest.of(0, 5));
        verifyNoMoreInteractions(service);
    }

    @Test
    public void shouldReturnProductById() {
        when(service.findBy(1L)).thenReturn(new Product(1L, "Banana", 2.44, ""));

        Product product = service.findBy(1L);

        Assertions.assertThat(product).isNotNull();
        assertThat(product.getId(), is(1L));
        verify(service, times(1)).findBy(1L);
        verifyNoMoreInteractions(service);
    }

    @Test
    public void shouldNotReturnProductByInvalidId() {
        when(service.findBy(1L)).thenReturn(null);

        Product product = service.findBy(1L);
        Assertions.assertThat(product).isNull();

        verify(service, times(1)).findBy(1L);
        verifyNoMoreInteractions(service);
    }

    @Test
    public void shouldSaveProduct() {
        when(service.findBy(1L)).thenReturn(new Product(1L, "Banana", 2.44, ""));

        Product product = new Product(1L, "Batata", 30.20, "");
        service.save(product);
        verify(service, times(1)).save(product);
        verifyNoMoreInteractions(service);

    }

    @Test
    public void shouldRemoveProduct() {
        Product product = new Product(1L, "Batata", 30.20, "");
        when(service.findBy(product.getId())).thenReturn(product);
        doNothing().when(service).remove(product.getId());

        service.remove(product.getId());
        verify(service, times(1)).remove(product.getId());
        verifyNoMoreInteractions(service);
    }
}
