package com.felipemarcel.store.service;

import com.felipemarcel.store.repository.CustomerRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class CustomerServiceTest {

    @Autowired
    private CustomerService service;

    @MockBean
    private CustomerRepository repository;

    @TestConfiguration
    static class CustomerServiceTestContextConfiguration {

        @Bean
        public CustomerService customerService() {
            return new CustomerService();
        }
    }

    @Test
    public void contextLoads() {
        Assertions.assertThat(service).isNotNull();
    }
}
