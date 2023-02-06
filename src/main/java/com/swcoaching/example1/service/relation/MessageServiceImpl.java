package com.swcoaching.example1.service.relation;

import com.swcoaching.example1.controller.dto.MessageResponseDto;
import com.swcoaching.example1.controller.dto.MessageSaveRequestDto;
import com.swcoaching.example1.domain.relation.Message;
import com.swcoaching.example1.domain.relation.MessageRepository;
import com.swcoaching.example1.domain.user.User;
import com.swcoaching.example1.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class MessageServiceImpl implements MessageService {
    private final MessageRepository msgRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public Long save(MessageSaveRequestDto requestDto) {
        Message message = requestDto.toEntity();
        User user = userRepository.getReferenceById(requestDto.getUserId());

        message.setUser(user);

        return msgRepository.save(message).getId();
    }

    @Override
    @Transactional(readOnly = true)
    public MessageResponseDto findById(Long id) {
        System.out.println("MessageResponseDto findById: id=" + id);

        Message entity = msgRepository.findById(id)
                .orElseThrow(() -> new MessageNotFoundException(id));
        System.out.println("MsgService findById: content=" + entity.getContent());

        return new MessageResponseDto(entity);
    }
}
