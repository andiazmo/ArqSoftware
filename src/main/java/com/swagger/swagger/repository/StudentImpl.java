/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swagger.swagger.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.swagger.swagger.entity.Student;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Anyelo
 */
@Repository
public class StudentImpl {

    @Autowired
    StudentRepository StudentRepository;

    @Autowired
    RedisTemplate redisTemplate;

    private static final String KEY = "Student";

    public Boolean saveRule(Student user) {
        try {
            Map ruleHash = new ObjectMapper().convertValue(user, Map.class);
            redisTemplate.opsForHash().put(KEY, user.getName(), ruleHash);
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Student findByName(String name) {

        Map userMap = (Map) redisTemplate.opsForHash().get(KEY, name);
        Student user = new ObjectMapper().convertValue(userMap, Student.class);
        return user;
    }

}
