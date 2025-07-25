package com.example.demo.Entity;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import com.example.demo.constants.Sentiment;

@Document(collection = "data")
@Data
@NoArgsConstructor
public class Entry {
    @Id
    private String id;
    @NonNull
    private String title;
    private String content;
    private Sentiment Sentiment;


    private LocalDateTime date;

}
