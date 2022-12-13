package com.example.demo;

import java.util.Map;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
class MicroserviceBApplicationTests {

  @Test
  void customerNameShouldBeDave() {

    Map result = WebClient.builder().baseUrl("http://localhost:9081/addresses/1234").defaultHeader("Customer-Name", "Dave").build().get().retrieve().bodyToMono(Map.class).block();
    Assertions.assertEquals("Dave", result.get("customerName"), "result should contain 'Dave'");

  }

}
