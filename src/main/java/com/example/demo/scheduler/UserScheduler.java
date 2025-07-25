package com.example.demo.scheduler;

import com.example.demo.Entity.Entry;
import com.example.demo.Entity.User;
import com.example.demo.constants.Sentiment;
import com.example.demo.service.MailService;
import com.example.demo.service.UserRespositoryImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@Slf4j
public class UserScheduler {

    @Autowired
    private MailService mailService;

    @Autowired
    private UserRespositoryImpl userRespositoryImpl;

    @Scheduled(cron = "0 0/1 * ? * *")

    public void sendMail(){
        log.info("sending mail");
        List<User>  users = userRespositoryImpl.findthem();
        for (User user : users) {
            List<Entry> entries = user.getEntries();
            List<Sentiment> entry = entries.stream()
                    .filter(x -> x.getDate().isAfter(LocalDateTime.now().minus(30, ChronoUnit.DAYS)))
                    .map(x -> x.getSentiment()).collect(Collectors.toList());
            Map<Sentiment, Integer> sentimentMap = new HashMap<>();

            for (Sentiment sentiment : entry) {
                if (sentiment != null) {
                    sentimentMap.put(sentiment, sentimentMap.getOrDefault(sentiment, 0) + 1);
                }
            }
                Sentiment max = null;
                int maxcount = 0;
                for (Map.Entry<Sentiment, Integer> entry1 : sentimentMap.entrySet()) {
                    if (entry1.getValue() > maxcount) {
                        maxcount = entry1.getValue();
                        max = entry1.getKey();
                    }
                }
                if (max != null) {
                    mailService.send(user.getEmail(), "Your last 7 days sentiment is ", max.toString());
                }
        }
    }
}
