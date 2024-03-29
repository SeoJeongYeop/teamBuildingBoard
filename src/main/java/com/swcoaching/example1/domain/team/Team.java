package com.swcoaching.example1.domain.team;

import com.swcoaching.example1.domain.BaseTimeEntity;
import com.swcoaching.example1.domain.Status;
import com.swcoaching.example1.domain.user.User;
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
    @Column(length = 50, nullable = false, unique = true)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;
    @Column(length = 500)
    private String picture;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User owner;

    @Lob
    @Column(length = 100000)
    private String image;

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
        if (picture != null)
            this.picture = picture;
    }

    public void setImage(String imageBase) {
        this.image = imageBase;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public void setStatusNormal() {
        this.status = Status.NORMAL;
    }

    public void block() {
        this.status = Status.BLOCK;
    }

    public void delete() {
        this.status = Status.DELETE;
    }
}
