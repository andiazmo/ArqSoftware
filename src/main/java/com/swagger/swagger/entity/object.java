/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swagger.swagger.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author root
 */
@ApiModel("Model object")
@Entity
public class object {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @ApiModelProperty(value = "the object's id", required = false)
    private Long id;
    @ApiModelProperty(value = "the object's name", required = true)
    private String name;
    @ApiModelProperty(value = "the object's value", required = true)
    private String value;

    public object(Long id, String name, String value) {
        this.id = id;
        this.name = name;
        this.value = value;
    }

    public object() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setValue(String value) {
        this.value = value;
    }
    
    
    public void setObject(object o){
        this.setId(o.getId());
        this.setName(o.getName());
        this.setValue(o.getValue());
    }
    
    
    
}
