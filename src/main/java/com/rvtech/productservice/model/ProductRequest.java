package com.rvtech.productservice.model;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@Getter
@Setter
public class ProductRequest {
    //spring validations
    @NotNull
    private String productName;
    private String productType;
    private String description;
    private Boolean isEnabled;
    @NotNull
    private String productUrl;
}
