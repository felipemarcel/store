package com.felipemarcel.store.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.felipemarcel.store.model.Order;
import com.felipemarcel.store.model.Product;
import com.felipemarcel.store.service.OrderProductService;
import com.felipemarcel.store.service.OrderService;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(OrderController.class)
public class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderService service;

    @MockBean
    private ProductService productService;

    @MockBean
    private OrderProductService orderProductService;

    @Test
    public void contextLoads() {
        assertThat(service).isNotNull();
        assertThat(productService).isNotNull();
        assertThat(orderProductService).isNotNull();
        assertThat(mockMvc).isNotNull();
    }

    @Test
    public void shouldReturnAllOrders() throws Exception {
        Page<Order> orders = new PageImpl(Arrays.asList(new Order(), new Order()));

        when(service.findAll(PageRequest.of(0, 5))).thenReturn(orders);

        mockMvc.perform(get("/orders"))
                .andExpect(status().isOk());

        verify(service, times(1)).findAll(PageRequest.of(0, 5));
        verifyNoMoreInteractions(service);
    }

    @Test
    public void shouldReturnOrderById() throws Exception {
        Order order = new Order();
        when(service.findBy(1L)).thenReturn(order);

        mockMvc.perform(get("/orders/{id}", 1L)).andExpect(status().isOk());

        verify(service, times(1)).findBy(1L);
        verifyNoMoreInteractions(service);
    }

    @Test
    public void shouldNotReturnOrderByInvalidId() throws Exception {
        when(service.findBy(1L)).thenReturn(null);
        mockMvc.perform(get("/orders/{id}", 1)).andExpect(status().isOk());
        verify(service, times(1)).findBy(1L);
        verifyNoMoreInteractions(service);
    }

    @Test
    public void shouldSaveOrder() throws Exception {
        Order order = new Order(1L);
        when(service.save(any(Order.class))).thenReturn(order);
        mockMvc.perform(post("/orders")
                .contentType(APPLICATION_JSON)
                .content(asJsonString(order)))
                .andExpect(status().isCreated());
    }

    @Test
    public void shouldRemoveOrder() throws Exception {
        Order order = new Order(1L);
        when(service.findBy(order.getId())).thenReturn(order);
        doNothing().when(service).remove(order.getId());
        mockMvc.perform(delete("/orders/{id}", order.getId())).andExpect(status().isOk());

        verify(service, times(1)).remove(order.getId());
        verifyNoMoreInteractions(service);
    }

    @Test
    public void shouldSetPaid() throws Exception {
        Order order = new Order(1L);
        doNothing().when(service).setPaid(order.getId());
        mockMvc.perform(put("/orders/{id}", order.getId()))
                .andExpect(status().isOk());
        verify(service, times(1)).setPaid(order.getId());
        verifyNoMoreInteractions(service);
    }

    @Test
    public void shouldAddProduct() throws Exception {
        Order order = new Order(1L);
        Product product = new Product(1L, "Batata", 3.23, "");
        when(service.save(order)).thenReturn(order);
        when(productService.save(product)).thenReturn(product);

        doNothing().when(service).addProduct(order.getId(), product, 1);
        mockMvc.perform(put("/orders/{id}/products", order.getId())
                .contentType(APPLICATION_JSON)
                .content(asJsonString(product)))
                .andExpect(status().isOk());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
