package com.jeffrey.mongodemo01.test;

import com.jeffrey.mongodemo01.entity.StudentEntity;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

/**
 * @author lucksoul 王吉慧
 * @version 1.0
 * @date 2025/1/5 21:17
 */

@SpringBootTest
@Slf4j
public class MongoDBTest002Query {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    public void testQueryById() {
        // 查询所有
        mongoTemplate.findAll(StudentEntity.class).forEach(s -> {
            log.info(String.valueOf(s));
        });
        System.out.println("---------------------------------------------------");
        // 查询单个
        StudentEntity studentEntity = mongoTemplate.findById("677a20d47d8bdef055ed4e21", StudentEntity.class);
        System.out.println(studentEntity);
    }

    @Test
    public void testQueryOne() {
        Query query = new Query(Criteria.where("age").is(18));
        // 查询单个
        StudentEntity studentEntity = mongoTemplate.findOne(query,StudentEntity.class);
        log.info(String.valueOf(studentEntity));
    }

    @Test
    public void testQueryWhere() {
        Query query = new Query(Criteria.where("age").gte(18).lte(22));
        // 查询单个
        List<StudentEntity> studentEntities = mongoTemplate.find(query, StudentEntity.class);
        log.info(String.valueOf(studentEntities));
    }
}
