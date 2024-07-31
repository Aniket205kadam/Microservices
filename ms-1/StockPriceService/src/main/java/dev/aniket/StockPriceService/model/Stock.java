package dev.aniket.StockPriceService.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "stock_price_table")
public class Stock {
    @Id
    private Integer stockId;
    private String companyName;
    private Double stockPrice;
}
