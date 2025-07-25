package com.example.demo.repository;

import com.example.demo.Entity.ApiKeyValuePair;
import com.example.demo.Entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface APIRepository extends MongoRepository<ApiKeyValuePair, String> {
}
