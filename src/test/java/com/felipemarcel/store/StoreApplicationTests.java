package com.felipemarcel.store;

import com.felipemarcel.store.api.CustomerController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StoreApplicationTests {

	@Autowired
	private CustomerController customerController;

	@Test
	public void contextLoads() {
		assertThat(customerController).isNotNull();
	}

}
