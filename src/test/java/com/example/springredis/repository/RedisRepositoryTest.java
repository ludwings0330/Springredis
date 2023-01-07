package com.example.springredis.repository;

import com.example.springredis.model.Person;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
class RedisRepositoryTest {
    @Autowired
    private PersonRedisRepository repo;

    @Test
    @DisplayName("Redis save Test")
    void test() throws Exception {
        //given
        final Person p = new Person("Bae", 30);
        final Person p2 = new Person("Park", 29);


        final Person saved = repo.save(p);
        final Person saved2 = repo.save(p2);

        //when
        final Optional<Person> findPerson = repo.findById(saved.getId());
        final Optional<Person> findPerson2 = repo.findById(saved2.getId());
        //then
        Assertions.assertThat(findPerson.isPresent()).isEqualTo(Boolean.TRUE);
        Assertions.assertThat(findPerson.get().getAge()).isEqualTo(30);

        Assertions.assertThat(findPerson2.isPresent()).isEqualTo(Boolean.TRUE);
        Assertions.assertThat(findPerson2.get().getAge()).isEqualTo(29);
    }
}