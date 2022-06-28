package com.example.demo.service;


import com.example.demo.model.entity.Course;
import com.example.demo.model.entity.Topic;
import com.example.demo.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseServiceV0 {

    private final List<Course> courses = new ArrayList<>(List.of(
            new Course("1", "Satellite Communication", "Satellite Communication", "1"),
            new Course("2", "Antenna Theory", "Antenna Theory", "1"),
            new Course("3", "Computer Organization", "Computer Organization", "1"),
            new Course("4", "NuroVascular", "NuroVascular", "2"),
            new Course("5", "Ophthalmology", "Ophthalmology", "2"),
            new Course("6", "Finance", "Finance", "3"),
            new Course("7", "Trading", "Trading", "3")
    ));

    /**
     *  All Courses for a topic.
     */
    public List<Course> getCourses(String topicId) {
        return courses.stream().filter(c -> c.getTopic().getId().equals(topicId)).collect(Collectors.toList());
    }

    public Course getCourse(String courseId) {
        return courses.stream().filter(c -> c.getId().equals(courseId)).findFirst().orElse(null);
    }

    public void createCourse(String topicId, Course newCourse) {
        newCourse.setTopic(new Topic(topicId, "", ""));
        courses.add(newCourse);
    }

    public void createCourses(String topicId, List<Course> newCourses) {
        newCourses.forEach(newCourse -> newCourse.setTopic(new Topic(topicId, "", "")));
        courses.addAll(newCourses);
    }

    public void modifyCourse(String topicId, String courseId, Course updatedCourse) {
        Course currentCourse  = courses.stream().filter(c -> c.getId().equals(courseId)).findFirst().orElse(null);
        if (currentCourse != null) {
            courses.removeIf(c -> c.getId().equals(currentCourse.getId()));
            updatedCourse.setTopic(new Topic(topicId, "", ""));
            courses.add(updatedCourse);
        }
    }

    public void deleteCourse(String courseId) {
        courses.removeIf(c -> c.getId().equals(courseId));
    }
}
