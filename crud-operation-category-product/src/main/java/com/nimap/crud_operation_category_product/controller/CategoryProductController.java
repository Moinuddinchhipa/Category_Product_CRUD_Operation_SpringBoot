package com.nimap.crud_operation_category_product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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

import com.nimap.crud_operation_category_product.entity.Category;
import com.nimap.crud_operation_category_product.entity.Product;
import com.nimap.crud_operation_category_product.repository.CategoryRepository;
import com.nimap.crud_operation_category_product.repository.ProductRepository;

@RestController
@RequestMapping("/api")
public class CategoryProductController {
	    @Autowired
	    private CategoryRepository categoryRepository;

	    @Autowired
	    private ProductRepository productRepository;

	    // 1. GET all the categories 
	    @GetMapping("/categories")
	    public Page<Category> getAllCategories(@PageableDefault(size = 10) Pageable pageable) {
	        return categoryRepository.findAll(pageable);
	    }

	    // 2. POST - create a new category 
	    @PostMapping("/categories")
	    public ResponseEntity<Category> createCategory(@RequestBody Category category) {
	        Category savedCategory = categoryRepository.save(category);
	        return new ResponseEntity<>(savedCategory, HttpStatus.CREATED);
	    }

	    // 3. GET category by Id
	    @GetMapping("/categories/{id}")
	    public ResponseEntity<Category> getCategoryById(@PathVariable Long id) {
	        return categoryRepository.findById(id)
	                .map(category -> new ResponseEntity<>(category, HttpStatus.OK))
	                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	    }

	    // 4. PUT - update category by id
	    @PutMapping("/categories/{id}")
	    public ResponseEntity<Category> updateCategory(@PathVariable Long id, @RequestBody Category category) {
	        return categoryRepository.findById(id)
	                .map(existingCategory -> {
	                    existingCategory.setName(category.getName()); // Update the name
	                    Category updatedCategory = categoryRepository.save(existingCategory);
	                    return new ResponseEntity<>(updatedCategory, HttpStatus.OK);
	                })
	                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	    }

	    // 5. DELETE - Delete category by id
	    @DeleteMapping("/categories/{id}")
	    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
	        categoryRepository.findById(id)
	                .ifPresent(category -> categoryRepository.delete(category));
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204 No Content
	    }
	    
	 // 1. GET all the products
	    @GetMapping("/products")
	    public Page<Product> getAllProducts(@PageableDefault(size = 10) Pageable pageable) {
	        return productRepository.findAll(pageable);
	    }

	    // 2. POST - create a new product
	    @PostMapping("/products")
	    public ResponseEntity<Product> createProduct(@RequestBody Product product, @RequestParam Long categoryId) {
	        Category category = categoryRepository.findById(categoryId)
	                .orElseThrow(() -> new RuntimeException("Category not found"));

	        product.setCategory(category);
	        Product savedProduct = productRepository.save(product);
	        return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
	    }

	    // 3. GET product by Id (with category details)
	    @GetMapping("/products/{id}")
	    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
	        return productRepository.findById(id)
	                .map(product -> new ResponseEntity<>(product, HttpStatus.OK))
	                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	    }

	    // 4. PUT - update product by id
	    @PutMapping("/products/{id}")
	    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product, @RequestParam Long categoryId) {
	        return productRepository.findById(id)
	                .map(existingProduct -> {
	                    existingProduct.setName(product.getName());
	                    existingProduct.setPrice(product.getPrice());
	                    Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new RuntimeException("Category Not Found"));
	                    existingProduct.setCategory(category);
	                    Product updatedProduct = productRepository.save(existingProduct);
	                    return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
	                })
	                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	    }

	    // 5. DELETE - Delete product by id
	    @DeleteMapping("/products/{id}")
	    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
	        productRepository.findById(id)
	                .ifPresent(product -> productRepository.delete(product));
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    }

	    

	    

}
