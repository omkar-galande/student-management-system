package com.omkar.student_management.controller;


import com.omkar.student_management.entity.Student;
import com.omkar.student_management.service.StudentService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public Student saveStudent(@RequestBody Student student){
        return studentService.saveStudent(student);
    }
}



