package com.femaleinvest.trading.controller;

import com.femaleinvest.trading.dto.StockDto;
import com.femaleinvest.trading.service.StockService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/v1/stocks")
@AllArgsConstructor
public class StockController {
    private final StockService stockService;

    @GetMapping(value = "/{symbol}")
    public StockDto getStock(@PathVariable("symbol") String symbol) {
        return stockService.getStock(symbol);
    }

}
