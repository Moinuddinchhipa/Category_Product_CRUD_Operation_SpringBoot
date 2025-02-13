package com.nimap.crud_operation_category_product.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nimap.crud_operation_category_product.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

}
