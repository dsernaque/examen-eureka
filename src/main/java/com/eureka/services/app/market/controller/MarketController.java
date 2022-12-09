package com.eureka.services.app.market.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eureka.services.app.market.model.StockMarketResponse;
import com.eureka.services.app.market.service.StockMarketService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/market")
public class MarketController {
  
  @Autowired
  private StockMarketService service;
  
  @GetMapping(path = "/stock/{token}",
              produces = MediaType.APPLICATION_JSON_VALUE)
  public List<StockMarketResponse> getStock(@PathVariable(required = true) String token)
      throws IOException {
    log.info("Starting MarketController.getStock method");    
    return service.getStock(token);
  }
}
