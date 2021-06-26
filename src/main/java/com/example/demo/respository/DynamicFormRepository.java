package com.example.demo.respository;

import com.example.demo.model.DynamicForm;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface DynamicFormRepository extends MongoRepository<DynamicForm, String> {
    DynamicForm findByName(String formId);
}
