package com.legant.Product.DTO;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class CategoryRequestResponse {
    private Long id;
    private String category;
}
