package com.felipemarcel.store.service;

import com.felipemarcel.store.model.Order;
import com.felipemarcel.store.repository.OrderRepository;
import org.assertj.core.api.Assertions;
import org.junit.Ignore;
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
public class OrderServiceTest {

    @MockBean
    private OrderService service;

    @MockBean
    private OrderRepository repository;

    @TestConfiguration
    static class OrderServiceTestContextConfiguration {

        @Bean
        public OrderService orderService() {
            return new OrderService();
        }
    }

    @Test
    public void contextLoads() {
        Assertions.assertThat(service).isNotNull();
    }

    @Test
    public void shouldReturnAllOrders() {
        Page<Order> orders = new PageImpl<>(Arrays.asList(new Order(), new Order()));

        when(service.findAll(PageRequest.of(0, 5))).thenReturn(orders);

        Page<Order> recoveredOrders = service.findAll(PageRequest.of(0, 5));
        assertThat(recoveredOrders.getContent(), hasSize(2));
        verify(service, times(1)).findAll(PageRequest.of(0, 5));
        verifyNoMoreInteractions(service);
    }

    @Test
    public void shouldReturnProductById() {
        when(service.findBy(1L)).thenReturn(new Order(1L));

        Order order = service.findBy(1L);

        Assertions.assertThat(order).isNotNull();
        assertThat(order.getId(), is(1L));
        verify(service, times(1)).findBy(1L);
        verifyNoMoreInteractions(service);
    }

    @Test
    public void shouldNotReturnOrderByInvalidId() {
        when(service.findBy(1L)).thenReturn(null);

        Order order = service.findBy(1L);
        Assertions.assertThat(order).isNull();

        verify(service, times(1)).findBy(1L);
        verifyNoMoreInteractions(service);
    }

    @Test
    public void shouldSaveOrder() {
        when(service.findBy(1L)).thenReturn(new Order(1L));

        Order order = new Order(1L);
        service.save(order);
        verify(service, times(1)).save(order);
        verifyNoMoreInteractions(service);

    }

    @Test
    public void shouldRemoveOrder() {
        Order order = new Order(1L);
        when(service.findBy(order.getId())).thenReturn(order);
        doNothing().when(service).remove(order.getId());

        service.remove(order.getId());
        verify(service, times(1)).remove(order.getId());
        verifyNoMoreInteractions(service);
    }

    @Test
    @Ignore
    public void shouldSetPaid() {
        Order order = new Order(1L);
        doNothing().when(service).setPaid(order.getId());
        verify(service, times(1)).setPaid(order.getId());
        verifyNoMoreInteractions(service);
    }
}
