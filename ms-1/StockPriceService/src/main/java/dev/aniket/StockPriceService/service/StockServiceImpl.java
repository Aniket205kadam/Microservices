package dev.aniket.StockPriceService.service;

import dev.aniket.StockPriceService.dao.StockDao;
import dev.aniket.StockPriceService.exception.StockNotFoundException;
import dev.aniket.StockPriceService.model.Stock;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StockServiceImpl implements StockService {
    private final StockDao stockDao;

    @Override
    public Double fetchByCompanyName(String companyName) {
        Optional<Stock> stock = stockDao.findByCompanyName(companyName);

        if (stock.isEmpty()) {
            throw new StockNotFoundException("Enter Wrong Company Name");
        }

        return stock.get().getStockPrice();
    }
}
