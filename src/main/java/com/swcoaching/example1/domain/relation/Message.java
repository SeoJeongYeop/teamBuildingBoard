package com.swcoaching.example1.domain.relation;

import com.swcoaching.example1.domain.BaseTimeEntity;
import com.swcoaching.example1.domain.user.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class Message extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @Builder
    public Message(User user, String content) {
        this.user = user;
        this.content = content;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
