package com.btg.pactual.btg.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.btg.pactual.btg.models.Transaction;

public interface TransactionRepository extends MongoRepository<Transaction, String> {

}
