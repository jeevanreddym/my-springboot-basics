package com.example.demo.controller;

import com.example.demo.model.dto.FilterCriteria;
import com.example.demo.model.entity.Course;
import com.example.demo.service.CourseServiceV1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CourseController {
    @Autowired
    private CourseServiceV1 courseService;

    @GetMapping("/topics/{topicId}/courses")
    public List<Course> getCourses(@PathVariable String topicId) {
        return courseService.getCourses(topicId);
    }

    @GetMapping("/topics/{topicId}/courses/{courseId}")
    public Course getCourse(@PathVariable String topicId, @PathVariable String courseId) {
        return courseService.getCourse(courseId);
    }

    @PostMapping("/courses/search")
    public List<Course> searchCourses(
            @RequestParam String searchTerm,
            @RequestBody FilterCriteria filterCriteria) {
        return courseService.searchCourses(searchTerm, filterCriteria);
    }

    @PostMapping("/topics/{topicId}/courses")
    public void createCourse(@PathVariable String topicId, @RequestBody Course newCourse) {
        courseService.createCourse(topicId, newCourse);
    }

    @PostMapping("/courses/bulk")
    public void bulkCreateCourses(@RequestBody List<Course> newCources) {
        courseService.createCourses(newCources);
    }

    @PutMapping("/topics/{topicId}/courses/{courseId}")
    public void modifyCourse(@PathVariable String topicId, @PathVariable String courseId, @RequestBody Course updatedCourse) {
        courseService.modifyCourse(topicId, courseId, updatedCourse);
    }

    @DeleteMapping("/courses/{courseId}")
    public void deleteCourse(@PathVariable String courseId) {
        courseService.deleteCourse(courseId);
    }
}
