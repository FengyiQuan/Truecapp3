package com.example.truecapp3.repositories;

import com.example.truecapp3.models.Transaction;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, String> {
  @Query("SELECT c FROM Transaction c WHERE c.id =: id")
  Transaction getTransactionById(@Param("id") String id);
}
