package com.omkar.student_management.service;

import com.omkar.student_management.entity.Student;
import com.omkar.student_management.repository.StudentRepository;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {

        this.studentRepository = studentRepository;
    }

    public Student saveStudent(Student student){
        return studentRepository.save(student);
    }
}

