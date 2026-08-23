package com.omkar.student_management.controller;

import com.omkar.student_management.dto.StudentRequest;
import com.omkar.student_management.dto.StudentResponse;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PathVariable;
import com.omkar.student_management.entity.Student;
import com.omkar.student_management.service.StudentService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.omkar.student_management.exception.StudentNotFoundException;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public StudentResponse saveStudent(@Valid @RequestBody StudentRequest request){
        return studentService.saveStudent(request);
    }

    @GetMapping
    public List<StudentResponse > getAllStudent(){
        return studentService.getAllStudents();
    }
    @GetMapping("/{id}")
    public StudentResponse getStudentById(@PathVariable Long id){
        return studentService.getStudentById(id);
    }

    @PutMapping("/{id}")
    public StudentResponse updateStudent(
        @PathVariable Long id,
              @Valid @RequestBody StudentRequest request){
        return studentService.updateStudent(id, request);
    }

    @DeleteMapping("/{id}")
    public String deleteStudent(@PathVariable Long id){
        studentService.deleteStudent(id);
        return "Student deleted successfully";
    }

    @ExceptionHandler(StudentNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleStudentNotFound(StudentNotFoundException ex){
        return ex.getMessage();
    }
}



