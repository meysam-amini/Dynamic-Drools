package com.example.spring_angular.droolsConfig;

import com.example.spring_angular.model.Rule;
import com.example.spring_angular.repositories.RuleRepository;
import org.kie.api.KieBase;
import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.Message;
import org.kie.api.builder.Results;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class dynamicDrools {

    private  List<Rule> rules;
    private  Rule rule;
    private KieSession kieSession;
    private static final String RULES_PATH = "rules/";

    @Autowired
    private  RuleRepository ruleRepository;


//
//    public dynamicDrools(RuleRepository ruleRepository) {
//       this.ruleRepository=ruleRepository;
//    }


    public  void initDrools(){
        KieServices kieServices = KieServices.Factory.get();
        KieFileSystem kfs = kieServices.newKieFileSystem();
        rules= (List<Rule>) ruleRepository.findAll();
        String r="";
        r+="import org.json.JSONObject; ";
        for (int i=0;i<rules.size();i++){

      // example:     obj: JSONObject(!obj.isNull("color") && obj.getJSONObject("color").get("title") == "Black")
            r+="rule R"+i+" when "+"obj: JSONObject(obj.get('"+rules.get(i).getWhen_key()+"')=='"+rules.get(i).getWhen_value()
                    +"'); then obj.put('"+rules.get(i).getThen_key()+"','"+rules.get(i).getThen_value()+"'); end; ";

        }
        System.out.println("::::::::::::\n"+r+"/n:::::::::::");
        kfs.write("src/main/resources/rules/offer.drl", r);
        KieBuilder kieBuilder = kieServices.newKieBuilder( kfs ).buildAll();
        Results results = kieBuilder.getResults();
        if( results.hasMessages( Message.Level.ERROR ) ){
            System.out.println( results.getMessages() );
            throw new IllegalStateException( "### errors ###" );
        }
        KieContainer kieContainer =
                kieServices.newKieContainer( kieServices.getRepository().getDefaultReleaseId() );
        KieBase kieBase = kieContainer.getKieBase();
         kieSession = kieContainer.newKieSession();
    }

    public  void fireRules(){
        kieSession.fireAllRules();
    }


    public  KieSession getKieSession(){
        return kieSession;
    }
}