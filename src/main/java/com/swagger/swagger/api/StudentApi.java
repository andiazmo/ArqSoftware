/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swagger.swagger.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.swagger.swagger.entity.UserLogin;
import com.swagger.swagger.entity.Student;
import com.swagger.swagger.repository.StudentRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import java.net.*;
import java.util.Map;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import static redis.clients.jedis.Protocol.Keyword.KEY;


/**
 *
 * @author root
 */




@RestController
@RequestMapping("/student")
@Api(value = "Object microservice", description = "This API has a CRUD for students")
public class StudentApi {
    
    @Autowired
    StudentRepository or;
    
    @Autowired
    RedisTemplate redisTemplate;
    
    private static final DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    String referenceDate = "2021/03/14 12:45:48";
    
    /**
     *
     * @return
     */
    @GetMapping("/getstusers")
    @ApiOperation(value = "Get all objects", notes = "Get all students in db")
    public List<Student> getstudents(HttpServletRequest request){
        List<Student> students = new ArrayList<>();
        List<Student> students1 = new ArrayList<>();
        getClientIPAddress(request);
        System.out.println("IP"+getClientIPAddress(request));
        students1.add(new Student("11", "hkh", 33));
        String strDate = getDate();
        System.out.println("strDate:::"+strDate);
        or.findAll().forEach(students::add);
        System.out.println("Tamaño de la lista:::"+students.size());
        System.out.println("Nombre recuperado:::"+students.get(0).getName());
        System.out.println("Nombre lista:::"+students1.get(0).getName());
        
        String user = students.get(0).getName();
        String user1 = students.get(0).getName();
        
        if(user == user1) {
            System.out.println("Encontro al usuario");
            return students;
        }
        
        List<Student> students2 = new ArrayList<>();
        students2.add(new Student("0", "Error", 404));
            
            
        return students2;
                
    }
    
    @GetMapping("/getstudentbyname/{id}")
    public Student getStudentByName(@PathVariable String id){
        System.out.println("name:::"+id);
        
        Map userMap = (Map) redisTemplate.opsForHash().get(KEY, id);
        Student user = new ObjectMapper().convertValue(userMap, Student.class);
        
        return user;
    }
    
    
    //Metodo que valida os datos
    public void validData(){
        List<Student> students = new ArrayList<>();
        students.add(new Student("11", "Test1", 33));
    }
    
    
    //Metodo que obtiene lo fecha y hora actual
    public String getDate(){
        Date date = new Date();
        System.out.println(sdf.format(date));

        Calendar cal = Calendar.getInstance();
        System.out.println("sdf::: "+sdf.format(cal.getTime()));

        LocalDateTime now = LocalDateTime.now();
        System.out.println("dtf::: "+dtf.format(now));
        
        String strDate = dtf.format(now);
        
        return strDate;
        
    }
    
    //Metodo para obtener la ip de la peticion
    public String getClientIPAddress(HttpServletRequest request) { 
        if (request.getHeader("x-forwarded-for") == null) { 
          return request.getRemoteAddr(); 
        } 
        return request.getHeader("x-forwarded-for"); 
    }
    
   
    
    
    
    
    /**
     *
     * @param o
     * @return
     */
    @PostMapping("/inserstudent")
    @ApiOperation(value = "Insert a student", notes = "Insert a student to db")
    public Student inserstudent(@RequestBody Student o){
        System.out.println("in inserstudent");
        or.save(o);
        return o;
    }
    
    /**
     *
     * @param o
     * @return
     */
//    @PutMapping("/updateobject")
//    @ApiOperation(value = "Update an object", notes = "Update an object with id")
//    public object updateobject(@RequestBody object o){
//        System.out.println("in updateobject");
//        or.findById(o.getId()).map(obj->{
//            obj.setObject(o);
//            or.save(obj);
//            return o;
//        });
//        return o;
//    }
    
    /**
     *
     * @param id
     * @return
     */
//    @DeleteMapping("/deleteobject")
//    @ApiOperation(value = "Delete an object", notes = "Delete an object by id")
//    public String deleteobject(@RequestParam(value="id") Long id){
//        System.out.println("in deleteobject");
//        or.deleteById(id);
//        return "Object with id "+id+" was remove";
//    }
    
    
    
}
