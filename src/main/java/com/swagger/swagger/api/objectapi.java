/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swagger.swagger.api;

import com.swagger.swagger.entity.object;
import com.swagger.swagger.repository.objectrepo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 *
 * @author root
 */
@RestController
@RequestMapping("/object")
@Api(value = "Object microservice", description = "This API has a CRUD for objects")
public class objectapi {
    
    @Autowired
    objectrepo or;   
    
    /**
     *
     * @return
     */
    @GetMapping("/getobjects")
    @ApiOperation(value = "Get all objects", notes = "Get all objects in db")
    public List<object> getobjects(){
        System.out.println("in getobjects");
        return or.findAll();
    }
    
    /**
     *
     * @param id
     * @return
     */
    @PostMapping("/getobject")
    @ApiOperation(value = "Get an object", notes = "Get an objects by id")
    public object getobject(@RequestParam(value="id") Long id){
        System.out.println("in getobject");
        return or.findById(id).get();
    }
    
    /**
     *
     * @param o
     * @return
     */
    @PostMapping("/insertobject")
    @ApiOperation(value = "Insert an object", notes = "Insert an object to db")
    public object inserobject(@RequestBody object o){
        System.out.println("in inserobject");
        or.save(o);
        return o;
    }
    
    /**
     *
     * @param o
     * @return
     */
    @PutMapping("/updateobject")
    @ApiOperation(value = "Update an object", notes = "Update an object with id")
    public object updateobject(@RequestBody object o){
        System.out.println("in updateobject");
        or.findById(o.getId()).map(obj->{
            obj.setObject(o);
            or.save(obj);
            return o;
        });
        return o;
    }
    
    /**
     *
     * @param id
     * @return
     */
    @DeleteMapping("/deleteobject")
    @ApiOperation(value = "Delete an object", notes = "Delete an object by id")
    public String deleteobject(@RequestParam(value="id") Long id){
        System.out.println("in deleteobject");
        or.deleteById(id);
        return "Object with id "+id+" was remove";
    }
    
    
    
}
