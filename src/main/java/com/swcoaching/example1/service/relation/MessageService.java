package com.swcoaching.example1.service.relation;

import com.swcoaching.example1.controller.dto.MessageResponseDto;
import com.swcoaching.example1.controller.dto.MessageSaveRequestDto;

public interface MessageService {
    Long save(MessageSaveRequestDto requestDto);

    MessageResponseDto findById(Long id);
}
