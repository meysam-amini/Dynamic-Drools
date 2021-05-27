package com.example.spring_angular.controllers;

import com.example.spring_angular.model.myJson;
import com.example.spring_angular.repositories.JsonRepository;
import com.example.spring_angular.service.jsonService;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class JsonController {

    @Autowired
    private JsonRepository jsonRepository;

    @Autowired
    jsonService jsonService;

    @GetMapping("/json")
    public myJson getJson() throws Exception {
        Optional<myJson> oj= jsonRepository.findById(1L);
        if(!oj.isPresent())
            throw new Exception("no json found!");
        else{
            System.out.println("getting json: "+ oj.toString());
            return oj.get();
        }

    }

    @PostMapping("/json")
    void applyJson(@RequestBody String json) throws IOException, JSONException {
        System.out.println("applying json:::: "+json);
        String result=jsonService.fireRules(json);
        System.out.println("jsonArray after firing: "+result);

    }
}
