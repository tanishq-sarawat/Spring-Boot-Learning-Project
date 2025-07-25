package com.example.demo.api.response;

import com.example.demo.Entity.ApiKeyValuePair;
import com.example.demo.repository.APIRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class Appcache {
    @Autowired
    private APIRepository apiRepository;

    public Map<String , String> appcache = new HashMap<>();

    @PostConstruct
    public void init() {
        List<ApiKeyValuePair> apiKeyValuePairs = apiRepository.findAll();
        for (ApiKeyValuePair apiKeyValuePair : apiKeyValuePairs) {
            appcache.put(apiKeyValuePair.getKey(), apiKeyValuePair.getValue());
        }
    }

}
