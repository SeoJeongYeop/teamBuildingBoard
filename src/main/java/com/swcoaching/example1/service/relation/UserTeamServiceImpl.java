package com.swcoaching.example1.service.relation;

import com.swcoaching.example1.controller.dto.UserTeamResponseDto;
import com.swcoaching.example1.controller.dto.UserTeamSaveRequestDto;
import com.swcoaching.example1.domain.relation.UserTeamRelation;
import com.swcoaching.example1.domain.relation.UserTeamRepository;
import com.swcoaching.example1.domain.team.Team;
import com.swcoaching.example1.domain.team.TeamRepository;
import com.swcoaching.example1.domain.user.User;
import com.swcoaching.example1.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserTeamServiceImpl implements UserTeamService {
    private final UserTeamRepository userTeamRepository;
    private final UserRepository userRepository;
    private final TeamRepository teamRepository;

    @Override
    @Transactional
    public Long save(UserTeamSaveRequestDto requestDto, Long msgId) {
        System.out.println("relation service save: " + msgId);
        UserTeamRelation relationEntity = requestDto.toEntity();
        User user = userRepository.getReferenceById(requestDto.getUserId());
        Team team = teamRepository.getReferenceById(requestDto.getTeamId());

        relationEntity.setUser(user);
        relationEntity.setTeam(team);
        relationEntity.setMSg(msgId);
        relationEntity.waitTeam();

        return userTeamRepository.save(relationEntity).getId();
    }

    @Override
    @Transactional(readOnly = true)
    public Long quitTeam(Long id, UserTeamSaveRequestDto requestDto) {
        UserTeamRelation entity = userTeamRepository.findById(id)
                .orElseThrow(() -> new UserTeamNotFoundException(id));
        entity.quitTeam();
        return id;
    }

    @Override
    @Transactional(readOnly = true)
    public UserTeamResponseDto findById(Long id) {
        System.out.println("UserTeamResponseDto findById: id=" + id);

        UserTeamRelation entity = userTeamRepository.findById(id)
                .orElseThrow(() -> new UserTeamNotFoundException(id));
        System.out.println("Service findById: status=" + entity.getRelationStatus());
        System.out.println("Service findById: id=" + entity.getUser().getId());

        return new UserTeamResponseDto(entity);
    }
}
