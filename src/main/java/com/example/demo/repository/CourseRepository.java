package com.example.demo.repository;

import com.example.demo.model.entity.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, String>, JpaSpecificationExecutor<Course> {

    List<Course> findByTopicId(String topicId);



    List<Course> findByName(String courseName, Sort sort);

    Page<Course> findByDescription(String courseDescription, Pageable pageable);



    @Query("SELECT c FROM Course c WHERE " +
            "LOWER(c.name) LIKE LOWER(CONCAT('%',:searchTerm, '%')) OR " +
            "LOWER(c.description) LIKE LOWER(CONCAT('%',:searchTerm, '%'))")
    List<Course> findBySearchTerm(@Param("searchTerm") String searchTerm, Pageable pageRequest);
    //Page<Course> findBySearchTerm(@Param("searchTerm") String searchTerm, Pageable pageRequest);
    //Slice<Course> findBySearchTerm(@Param("searchTerm") String searchTerm, Pageable pageRequest);



}
