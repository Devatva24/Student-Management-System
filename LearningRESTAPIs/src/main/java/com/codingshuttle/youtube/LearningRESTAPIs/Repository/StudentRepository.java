package com.codingshuttle.youtube.LearningRESTAPIs.Repository;

import com.codingshuttle.youtube.LearningRESTAPIs.Entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {
}
