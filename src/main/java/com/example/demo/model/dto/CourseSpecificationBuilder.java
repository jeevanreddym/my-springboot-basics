package com.example.demo.model.dto;

import com.example.demo.model.entity.Course;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;


public class CourseSpecificationBuilder {

    public static Specification<Course> createSpecification(SpecFilter input) {

        switch (input.getOperator()){

            case EQUALS:
                return (root, query, criteriaBuilder) ->
                        criteriaBuilder.equal(root.get(input.getField()),
                                castToRequiredType(root.get(input.getField()).getJavaType(),
                                        input.getValue()));

            case NOT_EQUALS:
                return (root, query, criteriaBuilder) ->
                        criteriaBuilder.notEqual(root.get(input.getField()),
                                castToRequiredType(root.get(input.getField()).getJavaType(),
                                        input.getValue()));

            case GREATER_THAN:
                return (root, query, criteriaBuilder) ->
                        criteriaBuilder.gt(root.get(input.getField()),
                                (Number) castToRequiredType(
                                        root.get(input.getField()).getJavaType(),
                                        input.getValue()));

            case LESS_THAN:
                return (root, query, criteriaBuilder) ->
                        criteriaBuilder.lt(root.get(input.getField()),
                                (Number) castToRequiredType(
                                        root.get(input.getField()).getJavaType(),
                                        input.getValue()));

            case LIKE:
                return (root, query, criteriaBuilder) ->
                        criteriaBuilder.like(root.get(input.getField()),
                                "%"+input.getValue()+"%");

            case IN:
                return (root, query, criteriaBuilder) ->
                        criteriaBuilder.in(root.get(input.getField()))
                                .value(castToRequiredType(
                                        root.get(input.getField()).getJavaType(),
                                        input.getValues()));

            default:
                throw new RuntimeException("Operation not supported yet");
        }
    }
    private static Object castToRequiredType(Class fieldType, List<String> value) {
        List<Object> lists = new ArrayList<>();
        for (String s : value) {
            lists.add(castToRequiredType(fieldType, s));
        }
        return lists;
    }

    private static Object castToRequiredType(Class fieldType, String value) {
        if(fieldType.isAssignableFrom(Double.class)) {
            return Double.valueOf(value);
        } else if(fieldType.isAssignableFrom(Integer.class)) {
            return Integer.valueOf(value);
        } else if(Enum.class.isAssignableFrom(fieldType)) {
            return Enum.valueOf(fieldType, value);
        }
        return null;
    }

    public static Specification<Course> getSpecification(List<SpecFilter> filters){
        Specification<Course> specification = null;
        for (int i=0; i < filters.size(); i++) {
            SpecFilter filter = filters.get(i);
            if (i == 0) {
                specification = Specification.where(createSpecification((filter)));
            } else {
                specification = specification.and(createSpecification(filter));
            }
        }
        return specification;
    }
}
