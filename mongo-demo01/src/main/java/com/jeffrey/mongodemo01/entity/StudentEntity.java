package com.jeffrey.mongodemo01.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.aggregation.ArrayOperators;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;
import java.util.List;

/**
 * @author lucksoul 王吉慧
 * @version 1.0
 * @date 2025/1/5 16:10
 */

@Document(collection = "emp")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentEntity {
    @Id
    private String id;

    @Field
    private String name;

    @Field
    private String sex;

    @Field
    private Integer age;

    @Field
    private Date birthday;

    @Field
    private String address;

    @Field
    private List<String> hobbies;
}
