package com.example.demo.controller;

import com.example.demo.Impl.HttpClientService;
import com.example.demo.model.Users;
import com.example.demo.respository.UsersRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

@RestController
public class UsersController {
    @Autowired
    public UsersRepository userRepo;
    @Autowired
    public HttpClientService clientService;
@RequestMapping(value="/users/register",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
public ResponseEntity<Users> registerUsers(@RequestBody() Users body){
    body.setPassword(new BCryptPasswordEncoder().encode(body.password));
     Users user=userRepo.save(body);
    return new ResponseEntity<Users>(user, HttpStatus.OK);

}
    @RequestMapping(value="/users",method = RequestMethod.POST,produces = MediaType.ALL_VALUE)
    public ResponseEntity<Users> getUsersBYName(@RequestBody() Users body){
        try{
                Users user= (Users) userRepo.findByUserName(body.userName);
            if(user!=null && new BCryptPasswordEncoder().matches(body.password,user.getPassword()))
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
/*
* Reactive programming
* */
    @RequestMapping(value = "/users/fakeUsers",method = RequestMethod.GET)
    public Mono<Object> callClient(){
        AtomicReference<ArrayList> response= new AtomicReference<>(new ArrayList());
    return this.clientService.get("/photos").map(data->{
        ObjectMapper obj= new ObjectMapper();
        try {
           response.set(obj.readValue(data, ArrayList.class));
    return ResponseEntity.ok(response);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return ResponseEntity.internalServerError();
    });
    }
}
