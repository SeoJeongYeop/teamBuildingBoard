package com.swcoaching.example1.controller;

import com.swcoaching.example1.controller.dto.TeamResponseDto;
import com.swcoaching.example1.domain.team.Team;
import com.swcoaching.example1.service.team.TeamService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;


import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(TeamController.class)
public class TeamControllerTest {

    @MockBean(name = "teamService")
    private TeamService teamService;
    @Autowired
    private MockMvc mockMvc;

    @DisplayName("팀 조회 테스트")
    @Test
    @WithMockUser(roles = "USER")
    void testReadTeam() throws Exception {
        //given
        long id = 1L;
        String name = "name";
        String description = "description";
        String picture = "/static/test.png";
        when(teamService.findById(id))
                .thenReturn(new TeamResponseDto(Team.builder()
                        .name(name)
                        .description(description)
                        .picture(picture)
                        .build()));

        //when
        ResultActions resultActions = mockMvc.perform(get("/api/v1/teams/{id}", id))
                .andDo(print());

        //then
        resultActions
                .andExpect(jsonPath("$.name").value(is(name)))
                .andExpect(jsonPath("$.description").value(is(description)))
                .andExpect(jsonPath("$.picture").value(is(picture)));
    }
}
