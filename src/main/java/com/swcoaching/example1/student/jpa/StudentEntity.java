package com.swcoaching.example1.student.jpa;

import jakarta.persistence.*;

@Table(name = "student")
@Entity
public class StudentEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
  private int semester;
  private String githubId;


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
