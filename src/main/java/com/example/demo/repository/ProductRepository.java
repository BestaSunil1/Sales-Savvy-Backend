package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Product;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findByCategory_CategoryId(Integer category_id);
    
    @Query("SELECT p.category.categoryName FROM Product p WHERE p.product_id = :product_id")
    String findCategoryNameByProductId(int product_id);
}

