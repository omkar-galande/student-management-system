package com.omkar.student_management.controller;

import com.omkar.student_management.dto.StudentRequest;
import com.omkar.student_management.dto.StudentResponse;
import com.omkar.student_management.entity.Student;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.PathVariable;
import com.omkar.student_management.service.StudentService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.data.domain.Pageable;

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

    @GetMapping("/course/{course}")
    public Page<StudentResponse> getStudentByCourse(@PathVariable String course,Pageable pageable){
        return studentService.getStudentByCourse(course, pageable);
    }

    @GetMapping("/name/{name}")
    public Page<StudentResponse> getStudentsByName(@PathVariable String name, Pageable pageable) {
        return studentService.getStudentsByName(name, pageable);
    }

    @GetMapping("/search/filter")
    public List<StudentResponse> searchStudents(@RequestParam String name,
                                                @RequestParam String course){
        return studentService.searchStudents(name,course);
    }

    @GetMapping("/search/courses")
    public List<StudentResponse> getStudentsByCourses(
            @RequestParam String course1,
            @RequestParam String course2) {

        return studentService.getStudentsByCourses(course1, course2);
    }
//    @ExceptionHandler(StudentNotFoundException.class)
//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    public String handleStudentNotFound(StudentNotFoundException ex){
//        return ex.getMessage();
//    }
}



