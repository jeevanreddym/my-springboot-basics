package com.example.demo.service;


import com.example.demo.model.entity.Topic;
import com.example.demo.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TopicService {
    @Autowired
    private TopicRepository topicRepository;

    private final List<Topic> topics = new ArrayList<>(List.of(
            new Topic("1", "Engineering", "Engineering"),
            new Topic("2", "Medicine", "Medicine"),
            new Topic("3", "Economics", "Economics")
    ));



    public List<Topic> getTopics() {
        //return topics;
        List<Topic> topics = new ArrayList<>();
        topicRepository.findAll().forEach(topic -> topics.add(topic));
        return topics;
    }

    public Topic getTopic(String topicId) {
        //return topics.stream().filter(c -> c.getId().equals(topicId)).findFirst().orElse(null);
        return topicRepository.findById(topicId).orElse(null);
    }

    public void createTopic(Topic newTopic) {
        //topics.add(newTopic);
        topicRepository.save(newTopic);
    }

    public void createTopics(List<Topic> newTopics) {
        topicRepository.saveAll(newTopics);
    }

    public void modifyTopic(String topicId, Topic updatedTopic) {
//        Topic currentTopic  = topics.stream().filter(c -> c.getId().equals(topicId)).findFirst().orElse(null);
//        if (currentTopic != null) {
//            topics.removeIf(topic -> topic.getId().equals(currentTopic.getId()));
//            topics.add(updatedTopic);
//        }
        topicRepository.save(updatedTopic);
    }

    public void deleteTopic(String topicId) {
        //topics.removeIf(topic -> topic.getId().equals(topicId));
        topicRepository.deleteById(topicId);
    }
}
