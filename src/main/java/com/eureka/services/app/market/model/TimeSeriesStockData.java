package com.eureka.services.app.market.model;

import java.util.Date;
import java.util.Map;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TimeSeriesStockData {

  @SerializedName("Meta Data") 
  @Expose
  private MetaData  metaData;
  @SerializedName("Time Series (Daily)")
  @Expose
  private Map<Date, DailyPrices> timeSeriesDaily;
  
}
