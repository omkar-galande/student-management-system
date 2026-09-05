package com.omkar.student_management.repository;
import com.omkar.student_management.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Page<Student> findByCourse(String course, Pageable pageable);
    Page<Student> findByNameContainingIgnoreCase(String name, Pageable pageable);
    List<Student> findByNameContainingIgnoreCaseAndCourse(String name, String course);
    List<Student> findByCourseOrCourse(String course1,String course2);
}
