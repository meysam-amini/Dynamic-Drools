package com.example.spring_angular.service;

import com.example.spring_angular.droolsConfig.dynamicDrools;
import com.example.spring_angular.model.myJson;
import com.example.spring_angular.repositories.JsonRepository;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Iterator;

@Slf4j
@Service
public class jsonService {

    @Autowired
    dynamicDrools dynamicDrools;
    @Autowired
    JsonRepository jsonRepository;

    private KieSession session;
    private String key="";


    public String fireRules(String json) throws IOException, JSONException {


        session = dynamicDrools.getKieSession();

        JSONObject jsonObject = new JSONObject(json);
        Iterator<String> iterator = jsonObject.keys();
        while(iterator.hasNext()) {
             key = iterator.next();
        }
        log.info("key: {} ",key);
        JSONArray jsonArray = jsonObject.getJSONArray(key);
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jo = jsonArray.getJSONObject(i);
            if (session != null) {
                session.insert(jo);
                session.fireAllRules();
            }
        }
        saveJson(jsonArray.toString());
           return jsonArray.toString();
    }


    private void saveJson(String array_content){

        myJson j=jsonRepository.findById(1L).get();
        j.setContent("{"+key+":"+array_content+"}");
        log.info("saving: {}",j.toString());
        jsonRepository.save(j);
    }
}
