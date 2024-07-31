package dev.aniket.StockCalculationService.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "StockPriceService")
public interface StockClient {
    @GetMapping("/api/stockPrice/{companyName}")
    ResponseEntity<Double> getStockPrice(@PathVariable("companyName") String company);
}
