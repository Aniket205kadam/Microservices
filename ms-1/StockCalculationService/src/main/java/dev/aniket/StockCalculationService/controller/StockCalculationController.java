package dev.aniket.StockCalculationService.controller;

import dev.aniket.StockCalculationService.client.StockClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class StockCalculationController {
    private final StockClient stockClient;
    private Logger logger = LoggerFactory.getLogger(StockCalculationController.class);

    public StockCalculationController(StockClient stockClient) {
        this.stockClient = stockClient;
    }

    @GetMapping("/cals/{companyName}/{quantity}")
    public ResponseEntity<String> calculateStockPrices(@PathVariable("companyName") String company,
                                                  @PathVariable Double quantity)
    {
        ResponseEntity<?> responseEntity = null;
        Double totalPrice = null;

        try {
            responseEntity = stockClient.getStockPrice(company);
            int status = responseEntity.getStatusCode().value();

            if (status == 200) {
                Double price = (Double) responseEntity.getBody();
                totalPrice = price * quantity;
                String response = "The total price is: " + totalPrice;
                logger.info(response);
                responseEntity = new ResponseEntity<>(response, HttpStatus.OK);
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            responseEntity = new ResponseEntity<>(e.getMessage(), HttpStatus.OK);
        }
        return (ResponseEntity<String>) responseEntity;
    }
}
