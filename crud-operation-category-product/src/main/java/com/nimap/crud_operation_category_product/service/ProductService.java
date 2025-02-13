package com.nimap.crud_operation_category_product.service;

import java.awt.print.Pageable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page; 
import org.springframework.stereotype.Service;

import com.nimap.crud_operation_category_product.entity.Product;
import com.nimap.crud_operation_category_product.repository.ProductRepository;

@Service
class ProductService {
	@Autowired
    private ProductRepository productRepository;

    public Page<Product> getAllProducts(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

}
