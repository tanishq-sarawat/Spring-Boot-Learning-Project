package com.example.demo.Entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "api-key-value")
@Data
public class ApiKeyValuePair {
    public String key;
    public String value;
}
