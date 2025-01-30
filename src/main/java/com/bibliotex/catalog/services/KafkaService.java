package com.bibliotex.catalog.services;

import com.bibliotex.catalog.domain.dto.response.BookResponse;
import com.bibliotex.catalog.domain.dto.response.ComicResponse;
import com.bibliotex.catalog.domain.dto.response.MangaResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaService {
    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    private void sendMessage(String topicName, Object body) {
        kafkaTemplate.send(topicName, body);
    }

    public void sendMessageCreate(BookResponse response) {
        this.sendMessage("catalog-create-book", response);
    }

    public void sendMessageCreate(MangaResponse response) {
        this.sendMessage("catalog-create-manga", response);
    }

    public void sendMessageCreate(ComicResponse response) {
        this.sendMessage("catalog-create-manga", response);
    }

}
