package com.nimap.crud_operation_category_product.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nimap.crud_operation_category_product.entity.Product;

@RestController
public class ProductController {
	
	
	@PostMapping(value="/saveproduct")
	public Product saveAllProduct( @RequestBody  Product prod) {
		
		System.out.println(prod);
		
		return prod;
	}

}
