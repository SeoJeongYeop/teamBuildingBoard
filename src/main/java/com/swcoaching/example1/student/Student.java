package com.swcoaching.example1.student;

import com.swcoaching.example1.student.jpa.StudentEntity;

public class Student {
    private final Long id;
    private final String name;
    private final int semester;
    private final String githubId;

    public Student(Long id, String name, int semester, String githubId) {
        this.id = id;
        this.name = name;
        this.semester = semester;
        this.githubId = githubId;
    }

    public static Student of(StudentEntity studentEntity) {
        return new Student(studentEntity.getId(), studentEntity.getName(),
                studentEntity.getSemester(), studentEntity.getGithubId());
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    public int getSemester() {
        return semester;
    }
    public String getGithubId() {
        return githubId;
    }
}
