package com.legant.Product.DTO;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ProductResponseForShop
{
    private List<NewProductsResponse> newProductsResponses;
    private boolean next;
}
