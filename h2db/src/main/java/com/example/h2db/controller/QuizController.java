package com.example.h2db.controller;

import com.example.h2db.model.Quiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/quiz")
public class QuizController {

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/all")
    public ResponseEntity<Quiz> getAllQuiz() {
        String url = "https://opentdb.com/api.php?amount=10&category=9&difficulty=hard";
        HttpHeaders headers = new HttpHeaders();

        HttpEntity<Quiz> request = new HttpEntity<>(headers);

        ResponseEntity<Quiz> response = restTemplate.exchange(url, HttpMethod.GET, request, Quiz.class);
        System.out.println("Status code: " + response.getStatusCode() + " : " + response.getStatusCodeValue());
        System.out.println(response.getBody());
        return response;
    }
}
