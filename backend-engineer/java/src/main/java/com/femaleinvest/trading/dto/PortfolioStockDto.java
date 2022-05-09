package com.femaleinvest.trading.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class PortfolioStockDto {
    private String symbol;
    private int quantity;
}
