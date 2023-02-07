package com.swcoaching.example1.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.swcoaching.example1.controller.dto.PostsResponseDto;
import com.swcoaching.example1.controller.dto.PostsUpdateRequestDto;
import com.swcoaching.example1.domain.board.BoardEntity;
import com.swcoaching.example1.domain.board.BoardRepository;
import com.swcoaching.example1.domain.posts.PostsEntity;
import com.swcoaching.example1.domain.posts.PostsRepository;
import com.swcoaching.example1.service.posts.PostsService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PostsApiController.class)
public class PostsApiControllerTest {

    @MockBean
    private PostsService postsService;
    @MockBean
    private PostsRepository postsRepository;
    @MockBean
    private BoardRepository boardRepository;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @DisplayName("게시글 조회 테스트")
    @Test
    @WithMockUser(roles = "USER")
    public void Posts_read() throws  Exception {
        //given
        long id = 1L;
        String title = "title";
        String content = "content";
        String author = "author";

        when(postsService.findById(id))
                .thenReturn(new PostsResponseDto(PostsEntity.builder()
                        .title(title)
                        .content(content)
                        .author(author)
                        .build()));
        //when
        ResultActions resultActions = mockMvc.perform(get("/api/v1/posts/{id}", id))
                .andDo(print());
        //then
        resultActions
                .andExpect(jsonPath("$.title").value(is(title)))
                .andExpect(jsonPath("$.content").value(is(content)))
                .andExpect(jsonPath("$.author").value(is(author)));
    }

    @DisplayName("게시글 수정 테스트")
    @Test
    @WithMockUser(roles = "USER")
    public void Posts_edit() throws Exception{
        //given
        long id = 1L;

        String beforeTitle ="title";
        String beforeContent ="content";
        String beforeAuthor ="author";

        when(postsService.findById(id))
                .thenReturn(new PostsResponseDto(PostsEntity.builder()
                        .title(beforeTitle)
                        .content(beforeContent)
                        .author(beforeAuthor)
                        .build()));

        String title = "title2";
        String content = "content2";
        Long boardId = 1L;

        when(postsService.update(id, PostsUpdateRequestDto.builder()
                .title(title)
                .content(content)
                .boardId(boardId)
                .build()))
                .thenReturn(id);
        when(postsRepository.findById(id))
                .thenReturn(Optional.ofNullable(PostsEntity.builder()
                        .title("title")
                        .content("content")
                        .author("author")
                        .build()));
        when(boardRepository.findById(boardId))
                .thenReturn(Optional.ofNullable(BoardEntity.builder()
                        .title("board")
                        .remark("remark")
                        .build()));
        Map<String, Object> input = new HashMap<>();
        input.put("title", title);
        input.put("content", content);
        input.put("boardId", boardId);

        //when
        ResultActions updateActions = mockMvc.perform(put("/api/v1/posts/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(input))
                .with(csrf()))
                .andDo(print());
        //then
        updateActions
                .andExpect(status().isOk());
    }
}
