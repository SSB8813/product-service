package com.rvtech.productservice.service;

import com.rvtech.productservice.entity.Product;
import com.rvtech.productservice.model.ProductRequest;
import com.rvtech.productservice.model.ProductUpdateRequest;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;

public interface ProductService {
    public Product createProduct(ProductRequest request);

    public Product getProduct(Integer productId);

    public ResponseEntity<Product> updateProduct(Integer productId, ProductUpdateRequest request);

    public ResponseEntity<String> deleteProduct(Integer productId);
}
