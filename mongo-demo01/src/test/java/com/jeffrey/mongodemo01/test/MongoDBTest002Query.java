package com.jeffrey.mongodemo01.test;

import com.jeffrey.mongodemo01.entity.StudentEntity;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
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

    @Test
    public void testQueryWhere2() {
        Query query = new Query(Criteria.where("age").gte(18).lte(22));
        query.addCriteria(Criteria.where("name").is("张三4"));
        // 查询单个
        List<StudentEntity> studentEntities = mongoTemplate.find(query, StudentEntity.class);
        log.info(String.valueOf(studentEntities));
        // { "age" : { "$gte" : 18, "$lte" : 22}, "name" : "张三4"}

    }

    @Test
    public void testQueryWhere3() {
        Query query = new Query();
        Criteria criteria1 = new Criteria();
        Criteria nameCriteria = Criteria.where("name").is("张三6");
        Criteria age = Criteria.where("age").gte(18).lte(22);
        Criteria nameCriteria2 = Criteria.where("name").is("张三4");

        Criteria criteria = criteria1.orOperator(nameCriteria, age.andOperator(nameCriteria2));
        // 等同有MySQL的的
        // select * from student
        // where name = '张三6' or (age >= 18 and age <= 22 and name = '张三4')
        query.addCriteria(criteria);
        // 查询单个
        List<StudentEntity> studentEntities = mongoTemplate.find(query, StudentEntity.class);
        log.info(String.valueOf(studentEntities));
        // { "$or" : [{ "name" : "张三6"}, { "age" : { "$gte" : 18, "$lte" : 22}, "$and" : [{ "name" : "张三4"}]}]}

    }

    @Test
    public void testQueryWhere4() {
        String jsonQuery = "{\"$or\":[{\"name\":\"张三6\"},{\"age\":{\"$gte\":18,\"$lte\":22},\"$and\":[{\"name\":\"张三4\"}]}]}";
        Query query = new BasicQuery(jsonQuery);
        // 等同有MySQL的的
        // select * from student
        // where name = '张三6' or (age >= 18 and age <= 22 and name = '张三4')


        // 查询单个
        List<StudentEntity> studentEntities = mongoTemplate.find(query, StudentEntity.class);
        log.info(String.valueOf(studentEntities));
        // { "$or" : [{ "name" : "张三6"}, { "age" : { "$gte" : 18, "$lte" : 22}, "$and" : [{ "name" : "张三4"}]}]}

    }

    @Test
    public void testQueryPage() {
        // 查询所有
        // 第二页数据，每页5条数据
        Query query = new Query().skip(5).limit(5);
        mongoTemplate.find(query,StudentEntity.class).forEach(s -> {
            log.info(String.valueOf(s));
        });

    }

    @Test
    public void testQuerySort() {
        // 查询所有
        // 第二页数据，每页5条数据
        Query query = new Query().skip(5).limit(5).with(Sort.by(Sort.Direction.DESC,"age").and(Sort.by(Sort.Direction.ASC,"name")));
        mongoTemplate.find(query,StudentEntity.class).forEach(s -> {
            log.info(String.valueOf(s));
        });

    }


}
