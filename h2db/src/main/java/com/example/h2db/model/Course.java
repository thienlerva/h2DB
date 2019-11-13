package com.example.h2db.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name="COURSES")
public class Course {

    @Id
    @Column(name="course_id")
    int id;

    @Column(name="course_name")
    String name;

    @Column(name="course_key")
    @JsonProperty("$key")
    String key;
}
