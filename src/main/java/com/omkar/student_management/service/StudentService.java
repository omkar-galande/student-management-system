package com.omkar.student_management.service;

import com.omkar.student_management.entity.Student;
import com.omkar.student_management.exception.StudentNotFoundException;
import com.omkar.student_management.repository.StudentRepository;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.List;
import com.omkar.student_management.exception.StudentNotFoundException;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {

        this.studentRepository = studentRepository;
    }

    public Student saveStudent(Student student){
        return studentRepository.save(student);
    }

    public List<Student> getAllStudents(){
        return studentRepository.findAll();
    }

    public Student getStudentById(Long id) {
        return studentRepository.findById(id)

                .orElseThrow(() -> new StudentNotFoundException(
                        "Student not found with id: " + id
                ));
    }

    public Student updateStudent(Long id, Student newStudent){
        Student student =  studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException(
                "Student not found with id: "+ id
        ));

        student.setName(newStudent.getName());
        student.setAddress(newStudent.getAddress());
        student.setCourse(newStudent.getCourse());
        student.setEmail(newStudent.getEmail());
        student.setPhone(newStudent.getPhone());
        return studentRepository.save(student);
    }

}

