package com.bs.bookStore.repository;

import com.bs.bookStore.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
//    Optional<CartItem> findById (Long bookId);
}
