/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swagger.swagger.repository;

import com.swagger.swagger.entity.Student;
import groovy.cli.Option;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Anyelo
 */
@Repository
public interface StudentRepository extends JpaRepository<Student, String> {
    
    public Optional<Student> findByName(String name);


}
