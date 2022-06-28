package com.example.demo.model.dto;

import com.example.demo.model.entity.Course;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class CourseSpecification implements Specification<Course> {
    private List<SpecFilter> filters;

    public CourseSpecification() {
        this.filters = new ArrayList<>();
    }

    public void add(SpecFilter criteria) {
        this.filters.add(criteria);
    }

    @Override
    public Predicate toPredicate(Root<Course> root, CriteriaQuery<?> query, CriteriaBuilder builder) {

        //create a new predicate list
        List<Predicate> predicates = new ArrayList<>();

        //add add criteria to predicates
        for (SpecFilter criteria : filters) {
            if (criteria.getOperator().equals(QueryOperator.GREATER_THAN)) {
                predicates.add(builder.greaterThan(
                        root.get(criteria.getField()), criteria.getValue().toString()));
            } else if (criteria.getOperator().equals(QueryOperator.LESS_THAN)) {
                predicates.add(builder.lessThan(
                        root.get(criteria.getField()), criteria.getValue().toString()));
            } else if (criteria.getOperator().equals(QueryOperator.GREATER_THAN_EQUAL)) {
                predicates.add(builder.greaterThanOrEqualTo(
                        root.get(criteria.getField()), criteria.getValue().toString()));
            } else if (criteria.getOperator().equals(QueryOperator.LESS_THAN_EQUAL)) {
                predicates.add(builder.lessThanOrEqualTo(
                        root.get(criteria.getField()), criteria.getValue().toString()));
            } else if (criteria.getOperator().equals(QueryOperator.NOT_EQUALS)) {
                predicates.add(builder.notEqual(
                        root.get(criteria.getField()), criteria.getValue()));
            } else if (criteria.getOperator().equals(QueryOperator.EQUALS)) {
                predicates.add(builder.equal(
                        root.get(criteria.getField()), criteria.getValue()));
            } else if (criteria.getOperator().equals(QueryOperator.MATCH)) {
                predicates.add(builder.like(
                        builder.lower(root.get(criteria.getField())),
                        "%" + criteria.getValue().toString().toLowerCase() + "%"));
            } else if (criteria.getOperator().equals(QueryOperator.MATCH_END)) {
                predicates.add(builder.like(
                        builder.lower(root.get(criteria.getField())),
                        criteria.getValue().toString().toLowerCase() + "%"));
            } else if (criteria.getOperator().equals(QueryOperator.MATCH_START)) {
                predicates.add(builder.like(
                        builder.lower(root.get(criteria.getField())),
                        "%" + criteria.getValue().toString().toLowerCase()));
            } else if (criteria.getOperator().equals(QueryOperator.IN)) {
                predicates.add(builder.in(root.get(criteria.getField())).value(criteria.getValue()));
            } else if (criteria.getOperator().equals(QueryOperator.NOT_IN)) {
                predicates.add(builder.not(root.get(criteria.getField())).in(criteria.getValue()));
            }
        }

        return builder.and(predicates.toArray(new Predicate[0]));
    }
}
