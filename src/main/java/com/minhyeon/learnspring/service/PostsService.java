package com.minhyeon.learnspring.service;


import com.minhyeon.learnspring.config.auth.LoginUser;
import com.minhyeon.learnspring.config.auth.dto.SessionUser;
import com.minhyeon.learnspring.domain.posts.Posts;
import com.minhyeon.learnspring.domain.posts.repository.PostsRepository;
import com.minhyeon.learnspring.web.dto.PostsListResponseDto;
import com.minhyeon.learnspring.web.dto.PostsResponseDto;
import com.minhyeon.learnspring.web.dto.PostsSaveRequestDto;
import com.minhyeon.learnspring.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto){
        return postsRepository.save(requestDto.toEntity()).getId();
    }
    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto){
        Posts posts = postsRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("해당 게시글이 없습니다. id="+id));
        posts.update(requestDto.getTitle(),requestDto.getContent());

        return id;
    }
    @Transactional
    public void delete(Long id){
        Posts posts = postsRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("해당 게시글이 없습니다 id"+id));
        postsRepository.delete(posts);
    }
    @Transactional
    public List<PostsListResponseDto> findAllDesc(){
        return postsRepository.findALLDesc().stream().map(PostsListResponseDto::new)
                .collect(Collectors.toList());
    }
    public PostsResponseDto findById(Long id){
        Posts entity = postsRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException(("해당 게시글이 없습니다 id=")+id));
        return new PostsResponseDto(entity);
    }
}
