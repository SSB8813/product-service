package com.rvtech.productservice.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@Getter
@Setter
public class ProductUpdateRequest {
    private String productName;
    private String productType;
    private String description;
    private Boolean isEnabled;
}
