package com.swcoaching.example1.student;

import com.swcoaching.example1.student.jpa.StudentEntity;
import com.swcoaching.example1.student.jpa.StudentRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

    @DisplayName("Name으로 회원 조회 한다")
    @Test
    void findByName() {
        // given
        StudentEntity studentEntity = mock(StudentEntity.class);
        StudentRepository studentRepository = mock(StudentRepository.class);

        long testStudentId = 1L;
        String testStudentName = "test";
        int testStudentSemester = 6;
        String testStudentGithubId = "github";

        when(studentEntity.getId()).thenReturn(testStudentId);
        when(studentEntity.getName()).thenReturn(testStudentName);
        when(studentEntity.getSemester()).thenReturn(testStudentSemester);
        when(studentEntity.getGithubId()).thenReturn(testStudentGithubId);

        when(studentRepository.findByName(testStudentName)).thenReturn(studentEntity);

        // when
        StudentService studentService = new StudentServiceImpl(studentRepository);
        Student student = studentService.findByName(testStudentName);

        // then
        assertEquals(student.getId(), testStudentId);
        assertEquals(student.getName(), testStudentName);
        assertEquals(student.getSemester(), testStudentSemester);
        assertEquals(student.getGithubId(), testStudentGithubId);
    }
}
