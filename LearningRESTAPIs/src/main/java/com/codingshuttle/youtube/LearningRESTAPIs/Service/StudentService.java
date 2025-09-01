package com.codingshuttle.youtube.LearningRESTAPIs.Service;

import com.codingshuttle.youtube.LearningRESTAPIs.Dto.AddStudentRequestDto;
import com.codingshuttle.youtube.LearningRESTAPIs.Dto.StudentDto;

import java.util.List;
import java.util.Map;

public interface StudentService {
    List<StudentDto> getAllStudent();
    StudentDto getStudentById(Long id);

    StudentDto createNewStudent(AddStudentRequestDto addStudentRequestDto);

    void deleteStudentById(Long id);

    StudentDto updateStudent(Long id, AddStudentRequestDto addStudentRequestDto);

    StudentDto updatePartialStudent(Long id, Map<String, Object> updates);
}
