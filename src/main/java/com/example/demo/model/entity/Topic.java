package com.example.demo.model.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name="Topic")
@Data
@NoArgsConstructor
@AllArgsConstructor
// Resources: Topic, Course, Lesson.
// Topic can have multiple Courses & Course can have multiple Lessons.
public class Topic {

    @Id
    @Column(name="topicId")
    private String id;

    private String name;

    private String description;

}
