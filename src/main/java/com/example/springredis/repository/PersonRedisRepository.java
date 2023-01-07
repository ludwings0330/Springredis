package com.example.springredis.repository;

import com.example.springredis.model.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRedisRepository extends CrudRepository<Person, String> {
}
