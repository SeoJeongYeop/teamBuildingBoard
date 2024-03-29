package com.swcoaching.example1.domain.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.swcoaching.example1.domain.BaseTimeEntity;
import com.swcoaching.example1.domain.posts.PostsEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Table(name = "app_user")
@Entity
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column
    private String picture;

    @Lob
    @Column(length = 100000)
    private String image;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    public List<PostsEntity> posts = new ArrayList<>();

    @Builder
    public User(String name, String email, String picture, Role role) {
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.role = role;
    }

    public User update(String name, String picture) {
        this.name = name;
        this.picture = picture;

        return this;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setRoleUser() {
        this.role = Role.USER;
    }

    public String getRoleKey() {
        return this.role.getKey();
    }

    public void addPosts(PostsEntity posts) {
        this.posts.add(posts);
    }
}
