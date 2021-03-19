/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swagger.swagger.repository;

import com.swagger.swagger.entity.UserLogin;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Anyelo
 */
@Repository
public interface LoginRepository extends CrudRepository<UserLogin, String> {}

