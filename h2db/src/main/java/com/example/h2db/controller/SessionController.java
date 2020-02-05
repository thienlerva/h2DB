package com.example.h2db.controller;

import com.example.h2db.model.Student;
import com.example.h2db.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/session")
public class SessionController {

    @Autowired
    StudentRepository repo;

    @GetMapping("/")
    public String readCookie(@CookieValue(value = "username", defaultValue = "Atta") String username) {
        return "My name is: " + username;
    }

    @PostMapping("/create")
    public Student createCookie(HttpServletResponse response, @RequestBody Student student) {

        Cookie cookie = new Cookie("username", student.getName());
        cookie.setPath("/");
        response.addCookie(cookie);
        return repo.save(student);
    }

    @PostMapping("/save")
    public Student createStudent(HttpSession session, @RequestBody Student student) {
        session.setAttribute("student", student);

        return repo.save(student);
    }

    @GetMapping("/session")
    public String handler(HttpSession session) {
        String key = "student";
        //session.setAttribute(key, "/usr/ncte/external");

        return "session id: " + session.getId() + ", directory: " + session.getAttribute(key);
    }
}
