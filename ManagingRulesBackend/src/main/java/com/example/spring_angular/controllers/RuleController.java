package com.example.spring_angular.controllers;

import com.example.spring_angular.droolsConfig.dynamicDrools;
import com.example.spring_angular.model.Rule;
import com.example.spring_angular.repositories.RuleRepository;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class RuleController {

    @Autowired
    private RuleRepository ruleRepository;

    @Autowired
    private dynamicDrools dynamicdrools;

    private KieSession session;

    @GetMapping("/rules")
    public List<Rule> getRules() {
        return (List<Rule>) ruleRepository.findAll();
    }

    @PostMapping("/rules")
    void addRules(@RequestBody Rule rule) {
        System.out.println(rule.toString());
        ruleRepository.save(rule);
    }

    @PostMapping("/deleterule")
    void deleteRule(@RequestBody long id) {
        System.out.println("delete: "+ ruleRepository.findById(id).toString());
        ruleRepository.deleteById(id);
    }

    @PostMapping("/editrule")
    void updateRule(@RequestBody Rule rule) {
        System.out.println("update: "+ rule.toString());
        ruleRepository.save(rule);
    }

    @PostMapping("/applyrules")
    int initRules(@RequestBody int init) {
        System.out.println("applyRules::::::::: " + init);
        if (init == 1) {
            session=dynamicdrools.getKieSession();
            if (((List<Rule>) ruleRepository.findAll()).size() > 0)
                if (session != null){
                    session.destroy();
            dynamicdrools.initDrools();}
            return 1;
        }
        return 0;
    }

}