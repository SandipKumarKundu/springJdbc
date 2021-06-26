package com.example.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashMap;
import java.util.List;

@Document(collection = "dynamicForm")
public class DynamicForm {
    @Id
    public String id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String name;

    public List<HashMap<String, String>> getInput() {
        return input;
    }

    public void setInput(List<HashMap<String, String>> input) {
        this.input = input;
    }

    public List<HashMap<String,String>> input ;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


}
