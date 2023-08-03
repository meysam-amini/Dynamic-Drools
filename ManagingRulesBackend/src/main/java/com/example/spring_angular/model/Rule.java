package com.example.spring_angular.model;


import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Setter
@Getter
@ToString@AllArgsConstructor
@Entity@NoArgsConstructor
public class Rule implements Serializable {

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