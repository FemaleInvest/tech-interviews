package com.femaleinvest.trading.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;

@Configuration
public class AlpacaConfiguration {
    private final String alpacaMarketDataApiBaseUrl;
    private final String alpacaTradingApiBaseUrl;
    private final String alpacaKeyId;
    private final String alpacaSecretKey;

    AlpacaConfiguration(
            @Value("${alpaca.marketDataApiBaseUrl}") String alpacaMarketDataApiBaseUrl,
            @Value("${alpaca.tradingApiBaseUrl}") String alpacaTradingApiBaseUrl,
            @Value("${alpaca.keyId}") String alpacaKeyId,
            @Value("${alpaca.secretKey}") String alpacaSecretKey
    ) {
        this.alpacaMarketDataApiBaseUrl = alpacaMarketDataApiBaseUrl;
        this.alpacaTradingApiBaseUrl = alpacaTradingApiBaseUrl;
        this.alpacaKeyId = alpacaKeyId;
        this.alpacaSecretKey = alpacaSecretKey;
    }

    @Bean(name = "marketDataApi")
    public RestTemplate marketDataApi() {
        RestTemplate restTemplate = new RestTemplateBuilder()
                .defaultHeader("APCA-API-KEY-ID", alpacaKeyId)
                .defaultHeader("APCA-API-SECRET-KEY", alpacaSecretKey)
                .build();
        restTemplate.setUriTemplateHandler(
                new DefaultUriBuilderFactory(alpacaMarketDataApiBaseUrl)
        );
        return restTemplate;
    }

    @Bean(name = "tradingApi")
    public RestTemplate tradingApi() {
        RestTemplate restTemplate = new RestTemplateBuilder()
                .defaultHeader("APCA-API-KEY-ID", alpacaKeyId)
                .defaultHeader("APCA-API-SECRET-KEY", alpacaSecretKey)
                .build();
        restTemplate.setUriTemplateHandler(
                new DefaultUriBuilderFactory(alpacaTradingApiBaseUrl)
        );
        return restTemplate;
    }

}
