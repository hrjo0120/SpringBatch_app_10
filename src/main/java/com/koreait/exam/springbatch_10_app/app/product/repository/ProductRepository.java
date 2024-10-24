package com.koreait.exam.springbatch_10_app.app.product.repository;

import com.koreait.exam.springbatch_10_app.app.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
