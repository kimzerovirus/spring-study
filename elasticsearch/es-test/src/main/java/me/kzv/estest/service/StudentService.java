package me.kzv.estest.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.kzv.estest.entity.Student;
import me.kzv.estest.repository.StudentRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RequiredArgsConstructor
@Service
public class StudentService {

    private final StudentRepository studentRepository;
    
    public Mono<Student> createStudent(Student student) {
        return studentRepository.save(student);
    }
    
    public Mono<Student> updateStudent(String id, Student student) {
        return studentRepository.findById(id).flatMap(std -> {
            log.info("std-{}", std);
            std.setFirstName(student.getFirstName());
            std.setLastName(student.getLastName());
            std.setJoinDate(student.getJoinDate());
            std.setSubjects(student.getSubjects());
            std.setAge(student.getAge());
            std.setAddress(student.getAddress());
            return studentRepository.save(std);
        })
                .doOnError(e -> log.error(String.valueOf(e)));
    }
    
    public Mono<String> deleteStudent(String id) {
        return studentRepository.deleteById(id)
                .thenReturn("Student deleted successfully!");
    }
    
    public Flux<Student> getStudentByFirstName(String firstName) {
        return studentRepository.findByFirstName(firstName);
    }
    
    public Flux<Student> getAllStudents() {
        return studentRepository.findAll();
    }
}
