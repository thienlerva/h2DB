package com.example.h2db.controller;

import com.example.h2db.model.Course;
import com.example.h2db.model.Student;
import com.example.h2db.repository.CourseRepository;
import com.example.h2db.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin("*")
public class StudentController {

    @Autowired
    StudentRepository repo;

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/cookie")
    public String readCookie(@CookieValue(value = "username", defaultValue = "Atta") String username) {
        return "My name is: " + username;
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

    @GetMapping("/students")
    public List<Student> findAllStudent() {
        return repo.findAll();
        }

    @GetMapping(value = "/all")
    public Map<String, List<Student>> getAll() {

        List<Student> students = repo.findAll();
        Map<String, List<Student>> stringListMap = new HashMap<>();
        stringListMap.put("students", students);
        return stringListMap;
    }

    @GetMapping(value = "/findStudentId")
    public Map<String, List<Course>> findStudentId() {

        Optional<Student> student = repo.findById(1);
        Student opt = student.get();
        List<Course> courses = courseRepository.findAll();
        Map<String, List<Course>> studentMap = new HashMap<>();
        studentMap.put(opt.getName(), courses);
        return studentMap;
    }

    @GetMapping("/api")
    public ResponseEntity<Student> findAll() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        ResponseEntity<Student> response = restTemplate.exchange("localhost:8080/student/all",
                HttpMethod.GET, new HttpEntity<>(headers), Student.class);

        return response;
    }

//    @GetMapping("/api/all")
//    public Student getAllApi() {
//        return restService.findAll();
//    }

    @PostMapping("/create")
    public ResponseEntity<String> createStudents(@RequestBody Map<String, List<Student>> studentMap) {

        List<Student> students = studentMap.get("students");
        List<Student> response = repo.saveAll(students);

        if(response==null) {
            return new ResponseEntity<>("Unable to create", HttpStatus.CONFLICT);
        } else {
            return new ResponseEntity<>("success", HttpStatus.CREATED);
        }
    }

    @GetMapping("/home")
    public String home() {
        return "Welcome home";
    }
}
