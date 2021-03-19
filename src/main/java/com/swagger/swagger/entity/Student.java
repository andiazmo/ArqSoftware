/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swagger.swagger.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import org.springframework.data.redis.core.RedisHash;

/**
 *
 * @author Anyelo
 */
@RedisHash("Student")
@Entity
@NamedQuery(name = "Student.findByName", query = "SELECT p FROM Student0 p WHERE LOWER(p.name) = LOWER(?1)")
@Table(name="Student")
public class Student implements Serializable {
  
    public enum Gender { 
        MALE, FEMALE
    }
    @Id
    private String id;
    @Column(name="name")
    private String name;
    private Gender gender;
    private int grade;
    
    public Student(String id, String name, int grade) {
        this.id = id;
        this.name = name;
        this.grade = grade;
        
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }
    
    
    
    
    
}
