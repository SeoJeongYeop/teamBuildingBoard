package com.swcoaching.example1.controller.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
public class BoardResponseDtoTest {

    @Test
    public void lombok_function_test(){
        //given
        Long id = 1L;
        String title = "test";
        String remark = "test1";

        //when
        BoardResponseDto dto = new BoardResponseDto(id, title, remark);

        //then
        assertThat(dto.getId()).isEqualTo(id);
        assertThat(dto.getTitle()).isEqualTo(title);
        assertThat(dto.getRemark()).isEqualTo(remark);
        System.out.println("lombok_function_test Done");

    }
}
