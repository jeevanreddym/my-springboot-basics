package com.example.demo.service;


import com.example.demo.model.dto.FilterCriteria;
import com.example.demo.model.entity.Course;
import com.example.demo.model.entity.Topic;
import com.example.demo.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Service
public class CourseServiceV1 {

    @Autowired
    private CourseRepository courseRepository;

    /**
     *  All Courses for a topic.
     */
    public List<Course> getCourses(String topicId) {
        List<Course> courses = new ArrayList<>();
        courseRepository.findByTopicId(topicId).forEach(course -> courses.add(course));
        return courses;
    }

    public List<Course> searchCourses(String searchTerm, FilterCriteria filterCriteria) {

        Specification<Course> spec = new Specification<Course>() {
            @Override
            public Predicate toPredicate(Root<Course> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.like(root.get("name"), "%"+searchTerm+"%");
            }
        };

        //Specification<Course> spec = CourseSpecificationBuilder.getSpecification(filterCriteria.getFilters());

        Pageable sortedByName = PageRequest.of(0, 3, Sort.by("name"));

        List<Course> courses = new ArrayList<>();
        courseRepository.findAll(spec, sortedByName)
                .forEach(course -> courses.add(course));
        return courses;
    }

    public List<Course> allSearchMethods(String courseName, String courseDescription) {
        List<Course> courses = courseRepository.findByName(courseName, Sort.by("name"));

        //Pageable firstPageWithTwoElements = PageRequest.of(0, 2);
        //Pageable secondPageWithFiveElements = PageRequest.of(1, 5);
        //Pageable sortedByName = PageRequest.of(0, 3, Sort.by("name"));
        //Pageable sortedByPriceDesc = PageRequest.of(0, 3, Sort.by("price").descending());
        //Pageable sortedByPriceDescNameAsc = PageRequest.of(0, 5, Sort.by("price").descending().and(Sort.by("name")));
        //Pageable sortedByPriceDescNameAsc = new PageRequest(1, 10, Sort.Direction.ASC, "title", "description");
        Pageable pageable = PageRequest.of(0, 20);

        Page<Course> coursesPage = courseRepository.findByDescription(courseDescription, pageable);
        courses = coursesPage.getContent();

        return courses;
    }

    public Course getCourse(String courseId) {
        return courseRepository.findById(courseId).orElse(null);
    }

    public void createCourse(String topicId, Course newCourse) {
        newCourse.setTopic(new Topic(topicId, "", ""));
        courseRepository.save(newCourse);
    }

    public void createCourses(List<Course> newCourses) {
        newCourses.forEach(newCourse -> newCourse.setTopic(new Topic(newCourse.getTopic().getId(), "", "")));
        courseRepository.saveAll(newCourses);
    }

    public void modifyCourse(String topicId, String courseId, Course updatedCourse) {
        updatedCourse.setTopic(new Topic(topicId, "", ""));
        courseRepository.save(updatedCourse);
    }

    public void deleteCourse(String courseId) {
        courseRepository.deleteById(courseId);
    }
}
