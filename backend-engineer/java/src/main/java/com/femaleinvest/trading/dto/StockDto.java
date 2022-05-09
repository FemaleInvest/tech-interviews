package com.femaleinvest.trading.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class StockDto {
    private String symbol;
    private Double price;
}
