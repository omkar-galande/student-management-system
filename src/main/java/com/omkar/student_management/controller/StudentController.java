package com.omkar.student_management.controller;

import com.omkar.student_management.dto.StudentRequest;
import com.omkar.student_management.dto.StudentResponse;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.PathVariable;
import com.omkar.student_management.service.StudentService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.data.domain.Pageable;

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
    public Page<StudentResponse > getAllStudent(@PageableDefault(size = 5, page = 0) Pageable pageable){

        return studentService.getAllStudents(pageable);
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

//    @ExceptionHandler(StudentNotFoundException.class)
//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    public String handleStudentNotFound(StudentNotFoundException ex){
//        return ex.getMessage();
//    }
}



