package com.codingshuttle.youtube.LearningRESTAPIs.Service.impl;

import com.codingshuttle.youtube.LearningRESTAPIs.Dto.AddStudentRequestDto;
import com.codingshuttle.youtube.LearningRESTAPIs.Dto.StudentDto;
import com.codingshuttle.youtube.LearningRESTAPIs.Entity.Student;
import com.codingshuttle.youtube.LearningRESTAPIs.Repository.StudentRepository;
import com.codingshuttle.youtube.LearningRESTAPIs.Service.StudentService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    private final ModelMapper modelMapper;
    @Override
    public List<StudentDto> getAllStudent() {
        List<Student> students = studentRepository.findAll();
        List<StudentDto> studentDtoList = students.stream()
                .map(student -> modelMapper.map(student, StudentDto.class))
                .toList();
        return studentDtoList;
    }

    @Override
    public StudentDto getStudentById(Long id) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Student not found exception"));
        //StudentDto studentDto = new StudentDto(student.getId(), student.getName(), student.getEmail());
        StudentDto studentDto = modelMapper.map(student, StudentDto.class);
        return studentDto;
    }

    @Override
    public StudentDto createNewStudent(AddStudentRequestDto addStudentRequestDto) {
        Student newStudent = modelMapper.map(addStudentRequestDto, Student.class);
        Student student = studentRepository.save(newStudent);
        return modelMapper.map(student, StudentDto.class);
    }

    @Override
    public void deleteStudentById(Long id) {
        if(!studentRepository.existsById(id)) {
            throw new IllegalArgumentException("Student not found exception by id : " + id);
        }
        studentRepository.deleteById(id);
    }

    @Override
    public StudentDto updateStudent(Long id, AddStudentRequestDto addStudentRequestDto) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Student not found exception by id : " + id));
        modelMapper.map(addStudentRequestDto, student);
        // we can pass object as well as class in the model mapper -> if object, all the fields will get updated in the mentioned class
        // if class it will get updated or converted to the other class
        student = studentRepository.save(student);
        return modelMapper.map(student, StudentDto.class);
    }

    @Override
    public StudentDto updatePartialStudent(Long id, Map<String, Object> updates) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Student not found exception by id : " + id));
        updates.forEach((field, value) -> {
            switch(field) {
                case "name" : student.setName((String) value);
                break;
                case "email" : student.setEmail((String) value);
                break;
                default: throw new IllegalArgumentException("Field is not supported : " + field);
            }
        });
        Student savedStudent = studentRepository.save(student);
        return modelMapper.map(savedStudent, StudentDto.class);
    }

}
