package com.eureka.services.app.market.service.impl;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.eureka.services.app.market.model.DailyPrices;
import com.eureka.services.app.market.model.StockMarketResponse;
import com.eureka.services.app.market.model.TimeSeriesStockData;
import com.eureka.services.app.market.repository.MarketStockRepository;
import com.eureka.services.app.market.service.StockMarketService;
import com.eureka.services.app.market.util.Constants;

import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Slf4j
@Service
public class StockMarketServiceImpl implements StockMarketService {
  
  private MarketStockRepository repository;
  
  public StockMarketServiceImpl() {
    OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
    
    Retrofit retrofit = new Retrofit.Builder()
        .baseUrl("https://www.alphavantage.co/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(httpClient.build())
        .build();
    repository = retrofit.create(MarketStockRepository.class);
  }
  
  @Override
  public List<StockMarketResponse> getStock(String apikey) throws IOException {
    log.info("Strating StockMarketServiceImpl.getStock method");
    
    Call<TimeSeriesStockData>     data     = repository.getStock(Constants.FUNCTION,
        Constants.SYMBOL, apikey, null, null);
    Response<TimeSeriesStockData> response = data.execute();
    
    if (!response.isSuccessful()) {
      String error = response.errorBody() != null
          ? response.errorBody().string() : "Unknown error";
      log.error("Error in StockMarketServiceImpl.getStock method :" + error);
      throw new IOException(error);
    }
    
    return getStockMarketResponse(response.body().getTimeSeriesDaily());
  }
  
  private List<StockMarketResponse> getStockMarketResponse(Map<Date, DailyPrices> prices) {
    log.info("Strating StockMarketServiceImpl.getStockMarketResponse method");
    
    List<StockMarketResponse> lista = prices.entrySet().stream()
        .sorted(Collections.reverseOrder(Map.Entry.comparingByKey()))
        .map(data -> StockMarketResponse.builder()
            .date(new SimpleDateFormat("yyyy-MM-dd").format(data.getKey()))
            .openPrice(data.getValue().getOpen())
            .higherPrice(data.getValue().getHigh())
            .lowerPrice(data.getValue().getLow())
            .closingPriceVariation(data.getValue().getClose())
            .build())
        .collect(Collectors.toList());
    
    for (int i = 0; i < lista.size() - 1; i++) {
      lista.get(i).setClosingPriceVariation(lista.get(i).getClosingPriceVariation()
          .subtract(lista.get(i + 1).getClosingPriceVariation()));
    }
    return lista;
  }
}
