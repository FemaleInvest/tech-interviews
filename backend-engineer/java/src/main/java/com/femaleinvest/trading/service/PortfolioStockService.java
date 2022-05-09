package com.femaleinvest.trading.service;

import com.femaleinvest.trading.dto.PortfolioStockDto;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class PortfolioStockService {
    private final RestTemplate marketDataApi;
    private final RestTemplate tradingApi;

    PortfolioStockService(
            @Qualifier("marketDataApi") RestTemplate marketDataApi,
            @Qualifier("tradingApi") RestTemplate tradingApi
    ) {
        this.marketDataApi = marketDataApi;
        this.tradingApi = tradingApi;
    }

    public PortfolioStockDto getPortfolioStock(String symbol) {
        String path = UriComponentsBuilder
                .fromPath("/v2/positions/" + symbol)
                .toUriString();
        AlpacaGetPositionResponse alpacaGetPositionResponse = tradingApi.getForObject(path, AlpacaGetPositionResponse.class);
        return new PortfolioStockDto()
                .setSymbol(symbol)
                .setQuantity(Integer.parseInt(alpacaGetPositionResponse.getQty()));
    }

    public void buyPortfolioStock(String symbol, int quantity) {
        String path = UriComponentsBuilder
                .fromPath("/v2/orders")
                .toUriString();
        AlpacaPostOrderRequest alpacaPostOrderRequest = new AlpacaPostOrderRequest()
                .setSymbol(symbol)
                .setQty(quantity)
                .setSide("buy")
                .setType("market")
                .setTime_in_force("day");
        tradingApi.postForEntity(path, alpacaPostOrderRequest, String.class);

    }

    public void sellPortfolioStock(String symbol, int quantity) {
        String path = UriComponentsBuilder
                .fromPath("/v2/orders")
                .toUriString();
        AlpacaPostOrderRequest alpacaPostOrderRequest = new AlpacaPostOrderRequest()
                .setSymbol(symbol)
                .setQty(quantity)
                .setSide("sell")
                .setType("market")
                .setTime_in_force("day");
        tradingApi.postForEntity(path, alpacaPostOrderRequest, String.class);
    }

    @Data
    @Accessors(chain = true)
    private static class AlpacaPostOrderRequest {
        // Symbol, asset ID, or currency pair to identify the asset to trade
        private String symbol;
        // Number of shares to trade. Can be fractionable for only market and day order types.
        private int qty;
        // buy or sell
        private String side;
        // market, limit, stop, stop_limit, or trailing_stop
        private String type;
        // See https://alpaca.markets/docs/trading/orders/#time-in-force
        private String time_in_force;
    }

    @Data
    private static class AlpacaGetPositionResponse {
        // Asset ID
        private String asset_id;
        // Symbol name of the asset
        private String symbol;
        // Exchange name of the asset (ErisX for crypto)
        private String exchange;
        // Asset class name
        private String asset_class;
        // Average entry price of the position
        private String avg_entry_price;
        // The number of shares
        private String qty;
        // “long”
        private String side;
        // Total dollar amount of the position
        private String market_value;
        // Total cost basis in dollar
        private String cost_basis;
        // Unrealized profit/loss in dollars
        private String unrealized_pl;
        // Unrealized profit/loss percent (by a factor of 1)
        private String unrealized_plpc;
        // Unrealized profit/loss in dollars for the day
        private String unrealized_intraday_pl;
        // Unrealized profit/loss percent (by a factor of 1)
        private String unrealized_intraday_plpc;
        // Current asset price per share
        private String current_price;
        // Last day’s asset price per share based on the closing value of the last trading day
        private String lastday_price;
        // Percent change from last day price (by a factor of 1)
        private String change_today;
    }
}
