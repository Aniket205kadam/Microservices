package dev.aniket.StockPriceService.dao;

import dev.aniket.StockPriceService.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StockDao extends JpaRepository<Stock, Integer> {
    Optional<Stock> findByCompanyName(String companyName);
}
