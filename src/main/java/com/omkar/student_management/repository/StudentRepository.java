package com.omkar.student_management.repository;
import com.omkar.student_management.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByCourse(String course);
    List<Student> findByNameContainingIgnoreCase(String name);
}
