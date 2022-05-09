package com.femaleinvest.trading.controller;

import com.femaleinvest.trading.dto.PortfolioStockDto;
import com.femaleinvest.trading.service.PortfolioStockService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/v1/portfolio/stocks")
@AllArgsConstructor
public class PortfolioStockController {
    private final PortfolioStockService portfolioStockService;

    @GetMapping(value = "/{symbol}")
    public PortfolioStockDto getPortfolioStock(@PathVariable("symbol") String symbol) {
        return portfolioStockService.getPortfolioStock(symbol);
    }

    @PostMapping(value = "/{symbol}/buy")
    public void buyPortfolioStock(
            @PathVariable("symbol") String symbol,
            @RequestParam(value = "quantity") int quantity
    ) {
        portfolioStockService.buyPortfolioStock(symbol, quantity);
    }

    @PostMapping(value = "/{symbol}/sell")
    public void sellPortfolioStock(
            @PathVariable("symbol") String symbol,
            @RequestParam(value = "quantity") int quantity
    ) {
        portfolioStockService.sellPortfolioStock(symbol, quantity);
    }

}
