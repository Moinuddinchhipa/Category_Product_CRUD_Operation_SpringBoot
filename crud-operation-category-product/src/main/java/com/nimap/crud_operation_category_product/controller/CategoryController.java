package com.nimap.crud_operation_category_product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nimap.crud_operation_category_product.entity.Category;
import com.nimap.crud_operation_category_product.repository.CategoryRepository;
@RestController
//@RequestMapping("/api/categories")
public class CategoryController {
	@Autowired
	//private CategoryService categoryService;
    private CategoryRepository categoryRepository;

    
	@PostMapping(value="/savecategory")
//	public Category saveAllcategory( @RequestBody Category category) {
//		System.out.println(category);
//		return category;
//	}
	
	 public ResponseEntity<Category> createCategory(@RequestBody Category category) {
			Category savedCategory = categoryRepository.save(category);
	        return new ResponseEntity<>(savedCategory, HttpStatus.CREATED); // 201 Created
	    }
	

}
