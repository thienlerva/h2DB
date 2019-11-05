package com.example.h2db.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Entity
@Table
public class Student {



    @Id
    @Column
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "STUDENT_ID_SEQUENCE")
//    @SequenceGenerator(name = "STUDENT_ID_SEQUENCE", sequenceName = "STUDENT_ID_SEQUENCE")
    int id;

    @Column
    String name;


}
