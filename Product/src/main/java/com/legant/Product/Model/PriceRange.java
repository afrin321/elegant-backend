package com.legant.Product.Model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Builder
@Data
public class PriceRange {

    private BigDecimal minPrice;
    private BigDecimal maxPrice;
}
