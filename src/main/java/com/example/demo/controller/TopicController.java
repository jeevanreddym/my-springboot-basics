package com.example.demo.controller;

import com.example.demo.model.entity.Topic;
import com.example.demo.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("topics")
public class TopicController {
    @Autowired
    private TopicService topicService;

    @GetMapping
    public List<Topic> getTopics() {
        return topicService.getTopics();
    }

    @GetMapping("/{topicId}")
    public Topic getTopic(@PathVariable String topicId) {
        return topicService.getTopic(topicId);
    }

    @PostMapping
    public void createTopic(@RequestBody Topic newTopic) {
        topicService.createTopic(newTopic);
    }

    @PostMapping("/bulk")
    public void bulkCreateTopics(@RequestBody List<Topic> newTopics) {
        topicService.createTopics(newTopics);
    }

    @PutMapping("{topicId}")
    public void modifyTopic(@PathVariable String topicId, @RequestBody Topic updatedTopic) {
        topicService.modifyTopic(topicId, updatedTopic);
    }

    @DeleteMapping("{topicId}")
    public void deleteTopic(@PathVariable String topicId) {
        topicService.deleteTopic(topicId);
    }
}
