package com.example.demo.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
public class Course {

    @Id
    @Column(name="id")
    //@GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    private String name;
    private String description;

    @Temporal(TemporalType.DATE)
    private Date expiryDate;
    @ManyToOne
    private Topic topic;

    public Course(String id, String name, String description, String topicId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.topic = new Topic(topicId, "", "");
    }
}
