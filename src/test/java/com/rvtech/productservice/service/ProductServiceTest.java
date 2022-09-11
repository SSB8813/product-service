package com.rvtech.productservice.service;

import com.rvtech.productservice.entity.Product;
import com.rvtech.productservice.exception.ResourceNotFoundException;
import com.rvtech.productservice.repository.ProductRepository;
import org.hibernate.jdbc.Expectations;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @InjectMocks
    private ProductServiceImpl productService;

    @Mock
    private ProductRepository productRepository;


    @Test
    public void testGetSingle(){
        Mockito.when(productRepository.findById(100))
                .thenReturn(java.util.Optional.of(new Product(1, "cake", "cakes", "birthday cake", "/products/cakes", true, "System", null, null, null)));

        Product product = productService.getProduct(100);
        Assertions.assertEquals("cake", product.getProductName());
    }

    @Test()
    public void testGetSingleProdWithException(){
        Mockito.when(productRepository.findById(100))
                .thenThrow(new ResourceNotFoundException("Product not found"));

        Product product = productService.getProduct(100);

    }
}
