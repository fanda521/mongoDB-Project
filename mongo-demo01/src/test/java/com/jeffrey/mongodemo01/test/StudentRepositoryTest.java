package com.jeffrey.mongodemo01.test;

import com.jeffrey.mongodemo01.entity.StudentEntity;
import com.jeffrey.mongodemo01.repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Date;

/**
 * @author lucksoul 王吉慧
 * @version 1.0
 * @date 2025/1/12 21:12
 */
@SpringBootTest
@Slf4j
public class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

    @Test
    public void testInsert() {
        StudentEntity studentEntity = StudentEntity.builder()
                .id("1")
                .name("张三")
                .sex("男")
                .age(18)
                .birthday(new Date())
                .address("北京")
                .hobbies(Arrays.asList("打篮球", "打毛球"))
                .build();
        StudentEntity insert = studentRepository.insert(studentEntity);
        log.info("insert:{}", insert);
    }
}
