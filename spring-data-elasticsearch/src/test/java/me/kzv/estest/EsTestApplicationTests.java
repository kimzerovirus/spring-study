package me.kzv.estest;

import me.kzv.estest.entity.Address;
import me.kzv.estest.entity.Student;
import me.kzv.estest.entity.Subject;
import me.kzv.estest.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
class EsTestApplicationTests {

    @Autowired
    StudentRepository studentRepository;

    @Test
    void contextLoads() {
        var student = Student.builder().firstName("john").lastName("doe").joinDate(LocalDate.now()).subjects(List.of(new Subject("영어"))).address(new Address("정의로", 70)).build();
        studentRepository.save(student);
    }

}
