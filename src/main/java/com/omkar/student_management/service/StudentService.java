package com.omkar.student_management.service;

import com.omkar.student_management.dto.StudentRequest;
import com.omkar.student_management.dto.StudentResponse;
import com.omkar.student_management.entity.Student;
import com.omkar.student_management.exception.StudentNotFoundException;
import com.omkar.student_management.repository.StudentRepository;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {

        this.studentRepository = studentRepository;
    }

    public StudentResponse saveStudent(StudentRequest request){
        Student student  = new Student();
        student.setName(request.getName());
        student.setEmail(request.getEmail());
        student.setCourse(request.getCourse());
        student.setPhone(request.getPhone());
        student.setAddress(request.getAddress());
        student = studentRepository.save(student);
        StudentResponse response = new StudentResponse();
        response.setId(student.getId());
        response.setName(student.getName());
        response.setAddress(student.getAddress());
        response.setCourse(student.getCourse());
        response.setPhone(student.getPhone());
        response.setEmail(student.getEmail());
        return response;
    }

    public List<StudentResponse> getAllStudents(){
        List<Student> students = studentRepository.findAll();
        List<StudentResponse> responses = new ArrayList<>();

        for(Student student : students){
            StudentResponse response = new StudentResponse();
            response.setId(student.getId());
            response.setName(student.getName());
            response.setEmail(student.getEmail());
            response.setCourse(student.getCourse());
            response.setPhone(student.getPhone());
            response.setAddress(student.getAddress());

            responses.add(response);
        }
        return responses;
    }

    public StudentResponse getStudentById(Long id) {
       Student student = studentRepository.findById(id)


                .orElseThrow(() -> new StudentNotFoundException(
                        "Student not found with id: " + id
                ));
        StudentResponse response = new StudentResponse();
        response.setId(student.getId());
        response.setName(student.getName());
        response.setPhone(student.getPhone());
        response.setEmail(student.getEmail());
        response.setAddress(student.getAddress());
        response.setCourse(student.getCourse());
        return response;
    }

    public StudentResponse updateStudent(Long id, StudentRequest request){
        Student student =  studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException(
                "Student not found with id: "+ id
        ));

        student.setName(request.getName());
        student.setAddress(request.getAddress());
        student.setCourse(request.getCourse());
        student.setEmail(request.getEmail());
        student.setPhone(request.getPhone());
        student =  studentRepository.save(student);

        StudentResponse response = new StudentResponse();
        response.setId(student.getId());
        response.setName(student.getName());
        response.setEmail(student.getEmail());
        response.setCourse(student.getCourse());
        response.setPhone(student.getPhone());
        response.setAddress(student.getAddress());

        return response;
    }
    public void deleteStudent(Long id) {

        studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException(
                        "Student not found with id: " + id
                ));

        studentRepository.deleteById(id);
    }
}

