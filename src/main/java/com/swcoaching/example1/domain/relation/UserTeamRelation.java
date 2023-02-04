package com.swcoaching.example1.domain.relation;

import com.swcoaching.example1.domain.BaseTimeEntity;
import com.swcoaching.example1.domain.team.Team;
import com.swcoaching.example1.domain.user.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class UserTeamRelation extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "relation_status", nullable = false)
    private RelationStatus relationStatus;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @ManyToOne
    @JoinColumn(name = "teamId")
    private Team team;

    @Builder
    public UserTeamRelation(User user, Team team, RelationStatus relationStatus) {
        this.user = user;
        this.team = team;
        this.relationStatus = relationStatus;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public void quitTeam() {
        this.relationStatus = RelationStatus.QUIT;
    }
}
