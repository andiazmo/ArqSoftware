/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swagger.swagger.entity;

import java.io.Serializable;
import org.springframework.data.redis.core.RedisHash;

/**
 *
 * @author Anyelo
 */
@RedisHash("userLogin")
public class UserLogin implements Serializable{
    
    private String id;
    private String userLogin;
    private String passLogin;

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public String getPassLogin() {
        return passLogin;
    }

    public void setPassLogin(String passLogin) {
        this.passLogin = passLogin;
    }
    

    
}
