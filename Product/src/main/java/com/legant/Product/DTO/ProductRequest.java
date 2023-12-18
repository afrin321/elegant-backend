package com.legant.Product.DTO;

import com.legant.Product.Model.PriceRange;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class ProductRequest {

    private String category;
    private Long categoryId;
    private List<PriceRange> priceRanges;
    private String sortBy;
    private String searchString;
    private boolean descending;
    private int page;
    private int productPerPage;
    private int perPageDisplay;

}
