package com.bs.bookStore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseHistory extends JpaRepository<PurchaseHistory, Long> {
}
