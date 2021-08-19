package com.example.demo.controller;

import com.example.demo.model.DynamicForm;
import com.example.demo.model.Users;
import com.example.demo.respository.DynamicFormRepository;
import com.example.demo.respository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.Optional;
@RestController
public class DynamicFormController {
    @Autowired
    public DynamicFormRepository formRepo;
        @PreAuthorize("hasAuthority('USER')")
    @RequestMapping(value="/form",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DynamicForm> importForm(HttpSession session, Principal p, @RequestBody DynamicForm formId){
        try{
//            String userName=session.
//            System.out.println(new StringBuilder().append(session.getAttribute("SPRING_SECURITY_CONTEXT")).append("logged In").toString());
            DynamicForm form= formRepo.save(formId);
                return new ResponseEntity<>(form, HttpStatus.OK);
        }
        catch (Exception e){
            return  new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @RequestMapping(value="/form/{formId}",method = RequestMethod.GET,produces = MediaType.ALL_VALUE)
    public ResponseEntity<DynamicForm> findFormById( @PathVariable String formId){
        try{
            DynamicForm form= formRepo.findByName(formId);
            if(form!=null)
                return new ResponseEntity<>(form, HttpStatus.OK);
            else
                return  new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        catch (Exception e){
            return  new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
