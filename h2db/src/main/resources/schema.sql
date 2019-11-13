SET MODE Oracle;

create table STUDENTS (
    student_id int AUTO_INCREMENT PRIMARY KEY,
    student_name varchar2(50),
    
);

CREATE TABLE COURSES (
    course_id int AUTO_INCREMENT PRIMARY KEY,
    course_name varchar2(50),
    course_key varchar2(20)
);
