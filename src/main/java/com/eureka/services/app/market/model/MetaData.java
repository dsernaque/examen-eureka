package com.eureka.services.app.market.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class MetaData {
  
  @SerializedName("1. Information") 
  @Expose
  private String information;
  @SerializedName("2. Symbol") 
  @Expose
  private String simbol;
  @SerializedName("3. Last Refreshed") 
  @Expose
  private String lastRefreshed;
  @SerializedName("4. Output Size") 
  @Expose
  private String outputSize;
  @SerializedName("5. Time Zone") 
  @Expose
  private String timeZone; 
  
}
