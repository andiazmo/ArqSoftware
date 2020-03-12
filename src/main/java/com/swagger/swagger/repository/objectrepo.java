/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swagger.swagger.repository;

import com.swagger.swagger.entity.object;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author root
 */
public interface objectrepo extends JpaRepository<object,Long>{
    
}
