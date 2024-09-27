package com.btg.pactual.btg.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.btg.pactual.btg.models.User;

public interface UserRepository extends MongoRepository<User, String> {

}
