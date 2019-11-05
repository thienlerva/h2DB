package com.example.h2db.controller;

import com.example.h2db.model.Student;
import com.example.h2db.repository.StudentRepository;
import com.example.h2db.service.RestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class StudentController {

    @Autowired
    StudentRepository repo;

    @Autowired
    RestService restService;

    @Autowired
    RestTemplate restTemplate;

    @GetMapping(value = "/all")
    public List<Student> getAll() {
        return repo.findAll();
    }

    @GetMapping("/api")
    public ResponseEntity<Student> findAll() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);


        return restTemplate.exchange("localhost:8080/student/all", HttpMethod.GET, new HttpEntity<>(headers), Student.class);
    }

    @GetMapping("/api/all")
    public Student getAllApi() {
        return restService.findAll();
    }

    @GetMapping("/create")
    public ResponseEntity<Student> create() {

        Student student1 = new Student(4, "Anthony");
        student1 = repo.save(student1);

        if(student1==null) {
            return new ResponseEntity<Student>(HttpStatus.CONFLICT);
        } else {
            return new ResponseEntity<Student>(student1, HttpStatus.CREATED);
        }
    }

    @GetMapping("/home")
    public String home() {
        return "Welcome home";
    }
}
