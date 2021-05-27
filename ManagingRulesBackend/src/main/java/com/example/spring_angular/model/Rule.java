package com.example.spring_angular.model;


import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@ToString
public class Rule implements Serializable {

    public Rule(){

    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private  String when_key;
    private  String when_value;
    private  String then_key;
    private  String then_value;

    public Rule(String when_key, String when_value, String then_key, String then_value) {
        this.when_key = when_key;
        this.when_value = when_value;
        this.then_key = then_key;
        this.then_value = then_value;
    }

    }