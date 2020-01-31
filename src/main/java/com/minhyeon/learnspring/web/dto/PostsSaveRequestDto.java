package com.minhyeon.learnspring.web.dto;

import com.minhyeon.learnspring.config.auth.LoginUser;
import com.minhyeon.learnspring.config.auth.dto.SessionUser;
import com.minhyeon.learnspring.domain.posts.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;



@Getter
@NoArgsConstructor
public class PostsSaveRequestDto {
    private String title;
    private String content;
    private String author;
    @Builder
    public PostsSaveRequestDto(String title, String content,String author){
        this.title = title;
        this.content = content;
    }
    public Posts toEntity(){

        return Posts.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();
    }
}
