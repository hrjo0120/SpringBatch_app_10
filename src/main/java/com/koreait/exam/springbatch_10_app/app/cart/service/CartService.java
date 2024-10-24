package com.koreait.exam.springbatch_10_app.app.cart.service;

import com.koreait.exam.springbatch_10_app.app.cart.entity.CartItem;
import com.koreait.exam.springbatch_10_app.app.cart.repository.CartItemRepository;
import com.koreait.exam.springbatch_10_app.app.member.entity.Member;
import com.koreait.exam.springbatch_10_app.app.product.entity.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CartService {
    private final CartItemRepository cartItemRepository;

    public CartItem addItem(Member buyer, Product product) {
        CartItem oldCartItem = cartItemRepository.findByBuyerIdAndProductId(buyer.getId(), product.getId()).orElse(null);
        if (oldCartItem != null) {
            return oldCartItem;
        }
        CartItem cartItem = CartItem.builder()
                .buyer(buyer)
                .product(product)
                .build();
        cartItemRepository.save(cartItem);
        return cartItem;
    }

    public boolean removeItem(Member buyer, Product product) {
        CartItem oldCartItem = cartItemRepository.findByBuyerIdAndProductId(buyer.getId(), product.getId()).orElse(null);
        if (oldCartItem != null) {
            cartItemRepository.delete(oldCartItem);
            return true;
        }
        return false;
    }

    public boolean hasItem(Member buyer, Product product) {
        return cartItemRepository.existsByBuyerIdAndProductId(buyer.getId(), product.getId());
    }
}