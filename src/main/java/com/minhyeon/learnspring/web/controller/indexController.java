package com.minhyeon.learnspring.web.controller;

import com.minhyeon.learnspring.config.auth.LoginUser;
import com.minhyeon.learnspring.config.auth.dto.SessionUser;
import com.minhyeon.learnspring.domain.posts.Posts;
import com.minhyeon.learnspring.service.PostsService;
import com.minhyeon.learnspring.web.dto.PostsListResponseDto;
import com.minhyeon.learnspring.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class indexController {
    private final PostsService postsService;
    private final HttpSession httpSession;
    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user){
        model.addAttribute("posts",postsService.findAllDesc());
        if(user != null){
            model.addAttribute("usersName",user.getName());
        }
        return "index";
    }
    @GetMapping("/posts/save")
    public String postsSave(Model model,@LoginUser SessionUser user){
        if(user != null) {
            model.addAttribute("usersName", user.getName());
        }
        return "posts-save";
    }
    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id,Model model,@LoginUser SessionUser user){
        PostsResponseDto dto = postsService.findById(id);
        if(user != null) {
            model.addAttribute("usersName", user.getName());
        }
        model.addAttribute("post",dto);
        return "posts-update";
    }

    @GetMapping("/posts/read/{id}")
    public String postsRead(@PathVariable Long id, Model model,@LoginUser SessionUser user){
        PostsResponseDto dto = postsService.findById(id);
        if(user != null) {
            model.addAttribute("usersName", user.getName());
        }
        model.addAttribute("post",dto);
        if(dto.getAuthor().equals(user.getName())){
            model.addAttribute("writer","임의값");
        }//작성자
        return "posts-read";
    }
}
