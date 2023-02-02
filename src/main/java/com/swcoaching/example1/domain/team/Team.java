package com.swcoaching.example1.domain.team;

import com.swcoaching.example1.domain.BaseTimeEntity;
import com.swcoaching.example1.domain.Status;
import jakarta.persistence.*;
import lombok.*;

@ToString
@Getter
@NoArgsConstructor
@Table(name = "team")
@Entity
public class Team extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 50, nullable = false)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;
    @Column(length = 500)
    private String picture;

    @Column(nullable = false)
    private Status status;

    @Builder
    public Team(String name, String description, String picture, Status status) {
        this.name = name;
        this.description = description;
        this.picture = picture;
        this.status = status;
    }

    public void update(String name, String description, String picture) {
        this.name = name;
        this.description = description;
        this.picture = picture;
    }

    public void block() {
        this.status = Status.BLOCK;
    }

    public void delete() {
        this.status = Status.DELETE;
    }
}
