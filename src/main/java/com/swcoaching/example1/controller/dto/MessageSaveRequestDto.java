package com.swcoaching.example1.controller.dto;

import com.swcoaching.example1.domain.relation.Message;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MessageSaveRequestDto {
    private String content;
    private Long userId;

    @Builder
    public MessageSaveRequestDto(String content, Long userId) {
        this.content = content;
        this.userId = userId;
    }

    public Message toEntity() {
        return Message.builder()
                .content(content)
                .build();
    }
}
