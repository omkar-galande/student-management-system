package com.omkar.student_management.mapper;

import com.omkar.student_management.dto.StudentRequest;
import com.omkar.student_management.dto.StudentResponse;
import com.omkar.student_management.entity.Student;
import org.springframework.stereotype.Component;

import javax.xml.stream.events.StartDocument;
@Component
public class StudentMapper {
    public StudentResponse toResponse(Student student){
        StudentResponse response = new StudentResponse();

        response.setId(student.getId());
        response.setName(student.getName());
        response.setEmail(student.getEmail());
        response.setCourse(student.getCourse());
        response.setPhone(student.getPhone());
        response.setAddress(student.getAddress());

        return response;
    }

    public Student toEntity(StudentRequest request) {
        Student student = new Student();

        student.setName(request.getName());
        student.setEmail(request.getEmail());
        student.setCourse(request.getCourse());
        student.setPhone(request.getPhone());
        student.setAddress(request.getAddress());

        return student;
    }
}
