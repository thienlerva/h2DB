package com.example.h2db.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name="STUDENTS")
public class Student {




//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "STUDENT_ID_SEQUENCE")
//    @SequenceGenerator(name = "STUDENT_ID_SEQUENCE", sequenceName = "STUDENT_ID_SEQUENCE")
    @Id
    @Column(name="student_id")
    int id;

    @Column(name="student_name")
    String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
