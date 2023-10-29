package me.kzv.estest.repository;

import me.kzv.estest.entity.Student;
import org.springframework.data.elasticsearch.repository.ReactiveElasticsearchRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface StudentRepository extends ReactiveElasticsearchRepository<Student, String> {
    Flux<Student> findByFirstName(String firstName);
}
