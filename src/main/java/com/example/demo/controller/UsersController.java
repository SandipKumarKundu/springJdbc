package com.example.demo.controller;

import com.example.demo.model.Users;
import com.example.demo.respository.UsersRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class UsersController {
    @Autowired
    public UsersRepository userRepo;
@RequestMapping(value="/users/register",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
public ResponseEntity<Users> registerUsers(@RequestBody() Users body){
     Users user=userRepo.save(body);
    return new ResponseEntity<Users>(user, HttpStatus.OK);

}
    @RequestMapping(value="/users",method = RequestMethod.POST,produces = MediaType.ALL_VALUE)
    public ResponseEntity<Users> getUsersBYName(@RequestBody() Users body){
        try{
            Users user= (Users) userRepo.findByUserNameAndPassword(body.userName,body.password);
            if(user!=null)
                return new ResponseEntity<Users>(user, HttpStatus.OK);
            else
                return  new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        catch (Exception e){
            return  new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @RequestMapping(value="/users/{userName}",method = RequestMethod.DELETE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deleteUsersBYName(@PathVariable(name="userName") String name){
        userRepo.deleteById(userRepo.findByFirstName(name).id);
        return new ResponseEntity<String>("deleted User "+name, HttpStatus.OK);

    }
}
