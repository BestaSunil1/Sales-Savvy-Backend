package com.example.demo.repository;

import com.example.demo.entity.CartItem;

import jakarta.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface CartRepository extends JpaRepository<CartItem, Integer> {
	
	

    // Fetch cart item for a given userId and productId
    @Query("SELECT c FROM CartItem c WHERE c.user.userId = :userId AND c.product.product_id = :product_id")
    Optional<CartItem> findByUserAndProduct(int user_id, int product_id);

    @Query("SELECT c FROM CartItem c JOIN FETCH c.product p LEFT JOIN FETCH ProductImage pi ON p.product_id = pi.product.product_id WHERE c.user.user_id = :user_id")
    List<CartItem> findCartItemsWithProductDetails(int userId);

    // Update quantity for a specific cart item
    @Query("UPDATE CartItem c SET c.quantity = :quantity WHERE c.id = :cartItemId")
    void updateCartItemQuantity(int cartItemId, int quantity);

    // Delete a product from the cart
    @Modifying
    @Transactional
    @Query("DELETE FROM CartItem c WHERE c.user.userId = :userId AND c.product.productId = :productId")
    void deleteCartItem(int userId, int productId);

    // Count the total quantity of items in the cart
    @Query("SELECT COALESCE(SUM(c.quantity), 0) FROM CartItem c WHERE c.user.userId = :userId")
    int countTotalItems(int userId);
    
    
    @Modifying
    @Transactional
    @Query("DELETE FROM CartItem c WHERE c.user.user_id = :user_id")
    void deleteAllCartItemsByUserId(int userId);
}
