package dev.aniket.StockPriceService.controller;

import dev.aniket.StockPriceService.service.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class StockController {
    private final StockService service;

    @GetMapping("/stockPrice/{companyName}")
    public ResponseEntity<Double> getStockPrice(@PathVariable("companyName") String company) {
        Double price = service.fetchByCompanyName(company);

        return new ResponseEntity<>(price, HttpStatus.OK);
    }
}
