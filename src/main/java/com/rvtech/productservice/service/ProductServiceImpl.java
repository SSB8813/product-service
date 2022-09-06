package com.rvtech.productservice.service;

import com.rvtech.productservice.entity.Product;
import com.rvtech.productservice.model.ProductRequest;
import com.rvtech.productservice.model.ProductUpdateRequest;
import com.rvtech.productservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Override
    public Product createProduct(ProductRequest request) {
        //Business Logic to create product
        Product newProduct = new Product();
        newProduct.setProductName(request.getProductName());
        newProduct.setProductUrl(request.getProductUrl());
        newProduct.setProductType(request.getProductType());
        newProduct.setProductDescription(request.getDescription());
        newProduct.setIsEnabled(request.getIsEnabled());
        newProduct.setCreatedBy("System");
        LocalDateTime datetime1 = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formatDateTime = datetime1.format(format);
        newProduct.setCreatedAt(datetime1);

        return productRepository.save(newProduct);

    }

    @Override
    public Product getProduct(Integer productId) {
        Optional<Product> product =
                productRepository.findById(productId);
        if(product.isEmpty()){
            return null;
        }
        return product.get();
    }

    @Override
    public ResponseEntity<Product> updateProduct(Integer productId, ProductUpdateRequest request) {
        Optional<Product> product = productRepository.findById(productId);
        if(product.isEmpty()){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        Product updateProduct = product.get();
        updateProduct.setProductName(request.getProductName());
        updateProduct.setProductType(request.getProductType());
        updateProduct.setProductDescription(request.getDescription());
        updateProduct.setIsEnabled(request.getIsEnabled());
        String productName = request.getProductName().replaceAll("\\s+", "-");
        updateProduct.setProductUrl("/products/gifts/"+productName);
        updateProduct.setModifiedBy("System");
        LocalDateTime datetime1 = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formatDateTime = datetime1.format(format);
        updateProduct.setModifiedAt(datetime1);

        productRepository.save(updateProduct);
        return new ResponseEntity<>(updateProduct, HttpStatus.ACCEPTED);
    }

    @Override
    public ResponseEntity<String> deleteProduct(Integer productId) {
        //validation of product before delete call
        Optional<Product> product = productRepository.findById(productId);
        if(product.isEmpty()){
            return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);
        }
        productRepository.deleteById(productId);
        return new ResponseEntity<>("Deleted product successfully", HttpStatus.OK);
    }
}
