package com.btg.pactual.btg.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.btg.pactual.btg.models.Funds;

public interface FundsRepository extends MongoRepository<Funds, String> {

}
