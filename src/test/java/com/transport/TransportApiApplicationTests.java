package com.transport;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

@SpringBootTest
class TransportApiApplicationTests {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	
	@Test
	void contextLoads() {
	}
	
//	@Test
//	void RegisterUserTest() {
//		assertThat(this.restTemplate.getForObject("http://localhost:"+port+"/"+"api/register", String.class))
//	}
	

}
