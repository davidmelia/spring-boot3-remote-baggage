package com.example.demo.controller;

import static org.springframework.http.ResponseEntity.ok;

import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@Slf4j
public class AddressController {

  private final Tracer tracer;

  public AddressController(Tracer tracer) {
    this.tracer = tracer;
  }

  @GetMapping(value = "/addresses/{addressId}", produces = MediaType.APPLICATION_JSON_VALUE)
  public Mono<ResponseEntity<Map<String, String>>> addressRetrieve(@PathVariable String addressId) {
    log.info("enter addressRetrieve():: {}", addressId);

    return Mono.just(tracer.getAllBaggage()).map(allBaggage -> {
      log.info("allBaggage {}", allBaggage);
      return allBaggage.get("Customer-Name") == null ? "" : allBaggage.get("Customer-Name");
    }).map(customerName -> ok(Map.of("customerName", customerName))).doOnSuccess(r -> log.info("exit addressSearch()", r));

  }

}
