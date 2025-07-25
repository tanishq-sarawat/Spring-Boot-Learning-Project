package com.example.demo.service;

import com.example.demo.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;


import java.util.List;
@Component
public class UserRespositoryImpl {

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<com.example.demo.Entity.User> findthem(){
        Query query = new Query();
        query.addCriteria(Criteria.where("email").regex("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,6}$"));
        query.addCriteria(Criteria.where("sentiment").is(true));
        List<com.example.demo.Entity.User> users1 = mongoTemplate.find(query, User.class);
        return users1;
    }
}
