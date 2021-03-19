/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swagger.swagger.api;

import ch.qos.logback.core.CoreConstants;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.swagger.swagger.entity.User;
import com.swagger.swagger.entity.Student;
import com.swagger.swagger.repository.UserRepository;
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
import java.text.ParseException;
import java.util.Map;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;
import static redis.clients.jedis.Protocol.Keyword.KEY;
import response.IncorrectLogin;


/**
 *
 * @author root
 */




@RestController
@RequestMapping("/user")
@Api(value = "Object microservice", description = "This API has a CRUD for users")
public class UserApi {
    
    @Autowired
    UserRepository or;
    
    @Autowired
    RedisTemplate redisTemplate;
    
    private static final DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    String referenceDate = "2021/03/14 12:45:48";
    
    /**
     *
     * @return
     */
    @GetMapping("/getUsers")
    @ApiOperation(value = "Get all objects", notes = "Get all users in db")
    public List<User> getUsers(HttpServletRequest request){
        List<User> users = new ArrayList<>();
        or.findAll().forEach(users::add);
           
        return users;
                
    }
    
    @GetMapping("/getuserbyname/{name}")
    public User getUserById1(@PathVariable String name){
        System.out.println("name:::"+name);
        
        Map userMap = (Map) redisTemplate.opsForHash().get(KEY, name);
        User user = new ObjectMapper().convertValue(userMap, User.class);
        System.out.println(""+user.toString());
        return user;
    }
    
    
    @PostMapping("/login")
    @ApiOperation(value = "Consult a user", notes = "Consult a user to db")
    public User insertUser(@RequestBody User o, HttpServletRequest request){
        System.out.println("in insert user");
        String ip = getClientIPAddress(request);
        String user = o.getUserLogin();
        
        //test2 127.0.0.1
        String id = user.trim()+" "+ip;
        System.out.println("ip:::"+ip);
        System.out.println("user peticion:::"+o.getUserLogin());
        System.out.println("password peticion:::"+o.getPassword());
        String date = getDate();
        String pass = o.getPassword();
        o.setDate(date);
        o.setId(user+" "+ip);
        List<User> users = new ArrayList<>();
        or.findAll().forEach(users::add);
        System.out.println("Tamaño de la lista:::"+users.size());
        
        for (int i = 0; i < users.size(); i++) {
        //    System.out.println("user Db:::"+users.get(i).getUserLogin());
        //    System.out.println("user peticion:::"+user);
        //    System.out.println("id Db:::"+users.get(i).getId());
        //    System.out.println("id peticion:::"+id);
            
            if(users.get(i).getId().equals(id)){
                System.out.println("Encontro al usuario");
                int countAux = users.get(i).getCount();
                if(users.get(i).getPassword().equals(pass)){
                    System.out.println("El password es correcto");
                    if(countAux == 3){
                        System.out.println("No puede pasar aunque la clave sea correcta");
                        throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Incorrect Login");
                        
                    }
                }
                else {
                    if(countAux < 3){
                        System.out.println("Puede pasar pero el password no es correcto");
                        o.setCount(countAux + 1);
                        o.setPassword(users.get(i).getPassword());
                    }
                    else {
                        System.out.println("No puede pasar");
                        String dateDB = o.getDate();
                        System.out.println("Fecha base de datos:::"+dateDB);
                        o.setCount(countAux + 1);
                        String strDate = getDate();
                        System.out.println("Fecha actual:::"+strDate);
                        Date fechaDate = null;
                    
                        try {
                            fechaDate = sdf.parse(dateDB);
                            System.out.println("fechaDate:::"+fechaDate);
                        }
                        catch(ParseException ex) {
                            System.out.println(ex);
                        
                        }
                    
                        o.setDate(strDate);
                        throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Incorrect Login");
                        
                    }
                    
                }//Else grande
                
            }
            
        }
        or.save(o);
        return null;
    }
    
    
    @GetMapping("/login1/{id}")
    @ApiOperation(value = "Get all objects", notes = "Get all users in db")
    public User login(@PathVariable String id, HttpServletRequest request){
        System.out.println("name:::"+id);
        String ip = getClientIPAddress(request);
        System.out.println("ip:::"+ip);
        List<User> users = new ArrayList<>();
        or.findAll().forEach(users::add);
        System.out.println("Tamaño de la lista:::"+users.size());
        
        for (int i = 0; i < users.size(); i++) {
            System.out.println("user:::"+users.get(i).getUserLogin());
            System.out.println("id:::"+users.get(i).getId());
            if (users.get(i).getId().equals(id)) {
                System.out.println("Encontro el id:::"+users.get(i).getId());
                System.out.println("count:::"+users.get(i).getCount());  
            }
        }
        
        return null;
    }
    
    
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
    @PostMapping("/insertuser")
    @ApiOperation(value = "Insert a user", notes = "Insert a user to db")
    public User insertUser1(@RequestBody User o, HttpServletRequest request){
        System.out.println("in insert user");
        String ip = getClientIPAddress(request);
        String user = o.getUserLogin();
        System.out.println("ip:::"+ip);
        System.out.println("user peticion:::"+o.getUserLogin());
        System.out.println("password peticion:::"+o.getPassword());
        String date = getDate();
        o.setDate(date);
        o.setId(user+" "+ip);
        or.save(o);
        return o;
    }
    
    
    //@GetMapping("/login")
    //@ApiOperation(value = "Get all objects", notes = "Get all users in db")
    public List<User> getUsersDB(HttpServletRequest request){
        List<User> users = new ArrayList<>();
        or.findAll().forEach(users::add);
           
        return users;
                
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
