package com.felipemarcel.store.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.felipemarcel.store.model.Product;
import com.felipemarcel.store.service.ProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService service;

    @Test
    public void contextLoads() {
        assertThat(service).isNotNull();
        assertThat(mockMvc).isNotNull();
    }

    @Test
    public void shouldReturnAllProducts() throws Exception {
        Page<Product> products = new PageImpl<>(Arrays.asList(
                new Product(1L, "Batata", 30.20, ""),
                new Product(2L, "Banana", 5.14, "")));

        when(service.findAll(PageRequest.of(0, 5))).thenReturn(products);

        mockMvc.perform(get("/products"))
                .andExpect(status().isOk());

        verify(service, times(1)).findAll(PageRequest.of(0, 5));
        verifyNoMoreInteractions(service);
    }

    @Test
    public void shouldReturnProductById() throws Exception {
        Product product = new Product(1L, "Batata", 30.20, "");
        when(service.findBy(1L)).thenReturn(product);

        mockMvc.perform(get("/products/{id}", 1L)).andExpect(status().isOk());

        verify(service, times(1)).findBy(1L);
        verifyNoMoreInteractions(service);
    }

    @Test
    public void shouldNotReturnProductByInvalidId() throws Exception {
        when(service.findBy(1L)).thenReturn(null);
        mockMvc.perform(get("/products/{id}", 1)).andExpect(status().isOk());
        verify(service, times(1)).findBy(1L);
        verifyNoMoreInteractions(service);
    }

    @Test
    public void shouldSaveProduct() throws Exception {
        Product product = new Product(1L, "Batata", 30.20, "");
        when(service.save(any(Product.class))).thenReturn(product);
        mockMvc.perform(post("/products")
                .contentType(APPLICATION_JSON)
                .content(asJsonString(product)))
                .andExpect(status().isCreated());
    }

    @Test
    public void shouldRemoveProduct() throws Exception {
        Product product = new Product(1L, "Batata", 30.20, "");
        when(service.findBy(product.getId())).thenReturn(product);
        doNothing().when(service).remove(product.getId());
        mockMvc.perform(delete("/products/{id}", product.getId())).andExpect(status().isOk());

        verify(service, times(1)).remove(product.getId());
        verifyNoMoreInteractions(service);
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
