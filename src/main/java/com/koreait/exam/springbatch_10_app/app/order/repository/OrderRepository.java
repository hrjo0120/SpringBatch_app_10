package com.koreait.exam.springbatch_10_app.app.order.repository;

import com.koreait.exam.springbatch_10_app.app.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
