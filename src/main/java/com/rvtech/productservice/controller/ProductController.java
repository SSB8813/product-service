package com.rvtech.productservice.controller;

import com.rvtech.productservice.entity.Product;
import com.rvtech.productservice.model.ProductRequest;
import com.rvtech.productservice.model.ProductUpdateRequest;
import com.rvtech.productservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/products")
    public ResponseEntity<Product> createProduct(
            @RequestBody ProductRequest productRequest){

        Product product = productService.createProduct(productRequest);
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    @GetMapping("/products/{productId}")
    public ResponseEntity<Product> getProduct(
            @PathVariable Integer productId
    ){
        Product product = productService.getProduct(productId);
       return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @PutMapping("/products/{productId}")
    public ResponseEntity<Product> updateProduct(
            @PathVariable Integer productId,
            @RequestBody ProductUpdateRequest request
            ){
        return productService.updateProduct(productId, request);
    }

    @DeleteMapping("/products/{productId}")
    public ResponseEntity<String> deleteProduct(
            @PathVariable Integer productId
    ){
        return productService.deleteProduct(productId);
    }

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts(
        @RequestParam(defaultValue = "0") Integer pageNo,
        @RequestParam(defaultValue = "10") Integer pageSize,
        @RequestParam(defaultValue = "productId") String sortBy
    ){
        List<Product> list = productService.getAllProducts(pageNo, pageSize, sortBy);

        return new ResponseEntity<>(list, HttpStatus.OK);
    }



}
