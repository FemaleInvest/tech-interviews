package com.femaleinvest.trading.service;

import com.femaleinvest.trading.dto.StockDto;
import lombok.Data;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Service
public class StockService {
    private final RestTemplate marketDataApi;

    StockService(
            @Qualifier("marketDataApi") RestTemplate marketDataApi
    ) {
        this.marketDataApi = marketDataApi;
    }

    public StockDto getStock(String symbol) {
        String path = UriComponentsBuilder
                .fromPath("/v2/stocks/" + symbol + "/trades/latest")
                .toUriString();
        AlpacaGetStockLatestTradeResponse alpacaGetStockLatestTradeResponse = marketDataApi.getForObject(
                path,
                AlpacaGetStockLatestTradeResponse.class
        );
        return new StockDto()
                .setSymbol(symbol)
                .setPrice(alpacaGetStockLatestTradeResponse.getTrade().getP());
    }

    @Data
    private static class AlpacaGetStockLatestTradeResponse {
        private String symbol;
        private Trade trade;

        @Data
        private static class Trade {
            // Timestamp in RFC-3339 format with nanosecond precision
            private String t;
            // Exchange where the trade happened
            private String x;
            // Trade price
            private Double p;
            // Trade size
            private int s;
            // Trade conditions
            private List<String> c;
            // Trade id
            private long i;
            // Tape
            private String z;
        }
    }
}
