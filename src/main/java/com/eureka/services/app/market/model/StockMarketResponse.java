package com.eureka.services.app.market.model;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class StockMarketResponse {
  private String date;
  private BigDecimal openPrice;
  private BigDecimal higherPrice;
  private BigDecimal lowerPrice;
  private BigDecimal closingPriceVariation;
}
