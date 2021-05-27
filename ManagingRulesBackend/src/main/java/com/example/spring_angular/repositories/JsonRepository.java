package com.example.spring_angular.repositories;

import com.example.spring_angular.model.myJson;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JsonRepository extends CrudRepository<myJson,Long> {
}
