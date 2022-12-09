package com.eureka.services.app.market.repository;

import com.eureka.services.app.market.model.TimeSeriesStockData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MarketStockRepository {
  
  @GET("query")
  public Call<TimeSeriesStockData> getStock(@Query("function") String function,
      @Query("symbol") String symbol, 
      @Query("apikey") String apikey,
      @Query("outputsize") String outputsize,
      @Query("datatype") String datatype);
}
