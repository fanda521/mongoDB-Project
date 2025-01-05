package com.jeffrey.mongodemo01.test;

import com.jeffrey.mongodemo01.entity.StudentEntity;
import com.mongodb.client.MongoCollection;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author lucksoul 王吉慧
 * @version 1.0
 * @date 2025/1/5 13:49
 */
@SpringBootTest
@Slf4j
public class MongoDBTest001 {

    @Autowired
    private MongoTemplate mongoTemplate;


    /**
     * 创建集合
     *
     */

    @Test
    public void testCreateColection() {
        boolean emp1 = mongoTemplate.collectionExists("emp");
        if (emp1) {
            mongoTemplate.dropCollection("emp");
            log.info("删除集合:emp成功!");
        }
        // 已经存在了集合，再创建是会报错额
        //Caused by: com.mongodb.MongoCommandException:
        // Command failed with error 48 (NamespaceExists):
        // 'Collection already exists. NS: java-study.emp' on server localhost:27017.
        // The full response is
        // {"ok": 0.0, "errmsg": "Collection already exists. NS: java-study.emp", "code": 48, "codeName": "NamespaceExists"}
        MongoCollection<Document> emp = mongoTemplate.createCollection("emp");
        log.info("创建集合:emp成功，emp={}",emp);
    }


    @Test
    public void testInsert() {
        StudentEntity student = StudentEntity.builder()
                .name("张三")
                .age(18)
                .sex("男")
                .address("北京市朝阳区")
                .birthday(new Date())
                .hobbies(Arrays.asList("吃鸡", "swimming", "看电视"))
                .build();

        // 相同的id 会报错
        mongoTemplate.insert(student);
        log.info("插入成功，student={}",student);

        // 相同的id 会更新，不存在则插入
        // 传入的集合名为空，则默认插入到默认的集合中；否则插入到指定的集合中
        mongoTemplate.save(student,"emp2");
    }

    @Test
    public void testInserBatch() {
        ArrayList<StudentEntity> studentEntities = new ArrayList<>();
        for (int i = 1; i <+ 10; i++) {
            StudentEntity student = StudentEntity.builder()
                    .name("张三"+i)
                    .age(18 + i)
                    .sex("男")
                    .address("北京市朝阳区" + i)
                    .birthday(new Date())
                    .hobbies(Arrays.asList("吃鸡" + i, "swimming" + i , "看电视" + i))
                    .build();
            studentEntities.add(student);
        }

        mongoTemplate.insert(studentEntities,StudentEntity.class);
    }


    @Test
    public void testDelete() {
        Query query = new Query();
        Criteria criteria = new Criteria();
        criteria.where("age").lt(18);

        query.addCriteria(criteria);
        List<StudentEntity> studentEntities = mongoTemplate.find(query, StudentEntity.class);

        System.out.println(studentEntities);
        mongoTemplate.remove(studentEntities);

    }






}
