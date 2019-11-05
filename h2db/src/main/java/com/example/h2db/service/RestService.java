package com.example.h2db.service;

import com.example.h2db.model.Student;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

@Service
public class RestService {

    private final RestTemplate restTemplate;

    public RestService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }


    public Student findAll() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Student> request = new HttpEntity<>(headers);
        ResponseEntity<Student> response = this.restTemplate.exchange("http://localhost:8080/student/findById/1", HttpMethod.GET, request, Student.class);
        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody();
        } else {
            return null;
        }
    }
}
