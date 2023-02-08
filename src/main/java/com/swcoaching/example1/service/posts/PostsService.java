package com.swcoaching.example1.service.posts;

import com.swcoaching.example1.controller.dto.PostsListResponseDto;
import com.swcoaching.example1.controller.dto.PostsResponseDto;
import com.swcoaching.example1.controller.dto.PostsSaveRequestDto;
import com.swcoaching.example1.controller.dto.PostsUpdateRequestDto;
import com.swcoaching.example1.domain.board.BoardEntity;
import com.swcoaching.example1.domain.board.BoardRepository;
import com.swcoaching.example1.domain.posts.PostsEntity;
import com.swcoaching.example1.domain.posts.PostsRepository;
import com.swcoaching.example1.domain.user.User;
import com.swcoaching.example1.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        PostsEntity postsEntity = requestDto.toEntity();
        BoardEntity boardEntity = boardRepository.getReferenceById(requestDto.getBoardId());
        postsEntity.setBoard(boardEntity);
        System.out.println(requestDto.getUserId());
        User user = userRepository.getReferenceById(requestDto.getUserId());
        postsEntity.setUser(user);

        System.out.println("PostService postsEntity user email");
        System.out.println(postsEntity.getUser().getEmail());

        return postsRepository.save(postsEntity).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        PostsEntity posts = postsRepository.findById(id)
                .orElseThrow(() -> new PostNotFoundException(id));

        posts.update(requestDto.getTitle(), requestDto.getContent());
        BoardEntity boardEntity = boardRepository.getReferenceById(requestDto.getBoardId());
        posts.setBoard(boardEntity);

        return id;
    }

    @Transactional
    public void delete(Long id) {
        PostsEntity posts = postsRepository.findById(id)
                .orElseThrow(() -> new PostNotFoundException(id));

        postsRepository.delete(posts);
    }

    @Transactional(readOnly = true)
    public PostsResponseDto findById(Long id) {
        PostsEntity entity = postsRepository.findById(id)
                .orElseThrow(() -> new PostNotFoundException(id));

        return new PostsResponseDto(entity);
    }

    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findAllDesc() {
        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findByBoardId(Long boardId) {
        return postsRepository.findByBoardId(boardId).stream()
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());

    }

    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findByUserIdDesc(Long userId) {
        return postsRepository.findByUserIdDesc(userId).stream()
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());

    }
}
