         package com.swcoaching.example1.student;

import com.swcoaching.example1.student.jpa.StudentEntity;
import com.swcoaching.example1.student.jpa.StudentRepository;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {

  private final StudentRepository studentRepository;

  public StudentServiceImpl(StudentRepository studentRepository)
  {
    this.studentRepository = studentRepository;
  }

  @Override
  public Student findByName(String name) {
    StudentEntity studentEntity = studentRepository.findByName(name);
    return Student.of(studentEntity);
  }
}
