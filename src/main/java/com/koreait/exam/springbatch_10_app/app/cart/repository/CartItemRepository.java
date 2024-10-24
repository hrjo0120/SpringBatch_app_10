package com.koreait.exam.springbatch_10_app.app.cart.repository;

import com.koreait.exam.springbatch_10_app.app.cart.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    Optional<CartItem> findByBuyerIdAndProductId(long buyerId, long productId);

    boolean existsByBuyerIdAndProductId(long buyerId, long productId);
}
