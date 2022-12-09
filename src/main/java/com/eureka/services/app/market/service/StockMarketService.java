package com.eureka.services.app.market.service;

import java.io.IOException;
import java.util.List;

import com.eureka.services.app.market.model.StockMarketResponse;

public interface StockMarketService {
  public List<StockMarketResponse> getStock(String apikey)
      throws IOException;
  
}
