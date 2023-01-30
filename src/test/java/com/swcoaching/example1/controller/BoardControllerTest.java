package com.swcoaching.example1.controller;

import com.swcoaching.example1.domain.board.Board;
import com.swcoaching.example1.service.board.BoardService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static java.util.Collections.emptyList;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BoardController.class)
class BoardControllerTest {

    @MockBean
    private BoardService boardService;
    @Autowired
    private MockMvc mockMvc;

    @DisplayName("게시판 정보를 조회 테스트 한다")
    @Test
    @WithMockUser(roles = "USER")
    void testBoard() throws Exception {
        // given
        long id = 1L;
        String title = "test";
        String remark = "test1";
        when(boardService.findById(id)).thenReturn(new Board(id, title, remark, emptyList()));

        // when
        ResultActions resultActions = mockMvc.perform(get("/board/{boardId}", id)).andDo(print());

        // then
        resultActions
                .andExpect(jsonPath("$.id").value(is((int) id)))
                .andExpect(jsonPath("$.title").value(is(title)))
                .andExpect(jsonPath("$.remark").value(is(remark)));
    }

    @DisplayName("BoardDto 리턴을 테스트 한다")
    @Test
    @WithMockUser(roles = "USER")
    public void return_boardDto() throws Exception {
        long id = 1L;
        String title = "test";
        String remark = "test1";
        mockMvc.perform(get("/board/dto")
                        .param("id", String.valueOf(id))
                        .param("title", title)
                        .param("remark", remark))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is((int) id)))
                .andExpect(jsonPath("$.title", is(title)))
                .andExpect(jsonPath("$.remark", is(remark)));
        System.out.println("return_boardDto DONE");
    }
}
