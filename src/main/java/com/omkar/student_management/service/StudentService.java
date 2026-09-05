package com.omkar.student_management.service;

import com.omkar.student_management.dto.StudentRequest;
import com.omkar.student_management.dto.StudentResponse;
import com.omkar.student_management.entity.Student;
import com.omkar.student_management.exception.StudentNotFoundException;
import com.omkar.student_management.mapper.StudentMapper;
import com.omkar.student_management.repository.StudentRepository;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.domain.Pageable;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    public final StudentMapper studentMapper;

    public StudentService(StudentRepository studentRepository, StudentMapper studentMapper) {

        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
    }


    public StudentResponse saveStudent(StudentRequest request){
        Student student = studentMapper.toEntity(request);

        student = studentRepository.save(student);

        return studentMapper.toResponse(student);
    }

    public Page<StudentResponse> getAllStudents(Pageable pageable){
        Page<Student> students = studentRepository.findAll(pageable);
        List<StudentResponse> responses = new ArrayList<>();

        for(Student student : students){
            responses.add(studentMapper.toResponse(student));
        }
        return students.map(studentMapper::toResponse);
    }

    public StudentResponse getStudentById(Long id) {
       Student student = studentRepository.findById(id)


                .orElseThrow(() -> new StudentNotFoundException(
                        "Student not found with id: " + id
                ));
        return studentMapper.toResponse(student);
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

        return studentMapper.toResponse(student);
    }
    public void deleteStudent(Long id) {

        studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException(
                        "Student not found with id: " + id
                ));

        studentRepository.deleteById(id);
    }

    public Page<StudentResponse> getStudentByCourse(String course,Pageable pageable){
        Page<Student> students =
                studentRepository.findByCourse(course,pageable);

        return students.map(studentMapper::toResponse);
    }

    public Page<StudentResponse> getStudentsByName(String name, Pageable pageable){
        Page<Student> students = studentRepository.findByNameContainingIgnoreCase(name, pageable);
        return students.map(studentMapper::toResponse);
    }

    public List<StudentResponse> searchStudents(String name, String course){
        List<Student> students = studentRepository.findByNameContainingIgnoreCaseAndCourse(name, course);
        return students.stream().map(studentMapper::toResponse).toList();
    }

    public List<StudentResponse> getStudentsByCourses(String course1, String course2) {
        List<Student> students =
                studentRepository.findByCourseOrCourse(course1, course2);

        return students.stream()
                .map(studentMapper::toResponse)
                .toList();
    }
}

