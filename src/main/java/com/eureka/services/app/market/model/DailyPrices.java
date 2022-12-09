package com.eureka.services.app.market.model;

import java.math.BigDecimal;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
public class DailyPrices {
  @SerializedName("1. open") 
  @Expose
  private BigDecimal open;
  @SerializedName("2. high") 
  @Expose
  private BigDecimal high;
  @SerializedName("3. low") 
  @Expose
  private BigDecimal low;
  @SerializedName("4. close") 
  @Expose
  private BigDecimal close;
  @SerializedName("5. volume") 
  @Expose
  private BigDecimal volume;
  
}
