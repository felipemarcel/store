package com.felipemarcel.store;

import com.felipemarcel.store.api.CustomerController;
import com.felipemarcel.store.api.OrderController;
import com.felipemarcel.store.api.ProductController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class StoreApplicationTests {

    @Autowired
    private CustomerController customerController;

    @Autowired
    private OrderController orderController;

    @Autowired
    private ProductController productController;

    @Test
    public void contextLoads() {
        assertThat(customerController).isNotNull();
        assertThat(orderController).isNotNull();
        assertThat(productController).isNotNull();
    }

}
