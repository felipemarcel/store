package com.felipemarcel.store.service;

import com.felipemarcel.store.model.Customer;
import com.felipemarcel.store.model.Order;
import com.felipemarcel.store.repository.OrderRepository;
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
public class OrderServiceTest {

    @Autowired
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
    public void shouldReturnAllCustomers() {
        Page<Order> orders = service.findAll(PageRequest.of(0, 5));
        if (orders != null) {
            assertThat(orders.getContent(), hasSize(0));
        }
    }
}
