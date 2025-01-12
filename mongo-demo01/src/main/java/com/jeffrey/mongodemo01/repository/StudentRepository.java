package com.jeffrey.mongodemo01.repository;

import com.jeffrey.mongodemo01.entity.StudentEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author lucksoul 王吉慧
 * @version 1.0
 * @date 2025/1/12 21:11
 */
@Repository
public interface StudentRepository extends MongoRepository<StudentEntity, String> {

}