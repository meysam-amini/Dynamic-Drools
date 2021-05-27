package com.example.spring_angular;

import com.example.spring_angular.droolsConfig.dynamicDrools;
import com.example.spring_angular.model.Rule;
import com.example.spring_angular.model.myJson;
import com.example.spring_angular.repositories.JsonRepository;
import com.example.spring_angular.repositories.RuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringAngularApplication {

    @Autowired
    private JsonRepository jsonRepository;

    @Autowired
    private dynamicDrools dynamicdrools;


    public static void main(String[] args) {
        SpringApplication.run(SpringAngularApplication.class, args);
    }



    @Bean
    CommandLineRunner init(RuleRepository ruleRepository) {
        Rule r = new Rule("language","Python", "edition","first");
        ruleRepository.save(r);
        dynamicdrools.initDrools();
        myJson j=new myJson();
        j.setId(1L);
        j.setContent("{\n" +
                "   \"eBooks\":[\n" +
                "      {\n" +
                "         \"language\":\"Pascal\",\n" +
                "         \"edition\":\"third\"\n" +
                "      },\n" +
                "      {\n" +
                "         \"language\":\"Python\",\n" +
                "         \"edition\":\"four\"\n" +
                "      },\n" +
                "      {\n" +
                "         \"language\":\"SQL\",\n" +
                "         \"edition\":\"second\"\n" +
                "      }\n" +
                "   ]\n" +
                "}");
        jsonRepository.save(j);


        return args -> {



        };
    }
}

