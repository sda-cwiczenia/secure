package com.sda.secure.controller;

import com.sda.secure.model.CategoryEnum;
import com.sda.secure.model.Comment;
import com.sda.secure.model.Post;
import com.sda.secure.service.CommentService;
import com.sda.secure.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class PostController {

    PostService postService;
    CommentService commentService;

    @Autowired
    public PostController(PostService postService, CommentService commentService) {
        this.postService = postService;
        this.commentService = commentService;
    }

    @GetMapping("/addpost")
    public String addPost(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("auth", auth);
        model.addAttribute("post", new Post());
        List<CategoryEnum> categories =
                new ArrayList<>(Arrays.asList(CategoryEnum.values()));
        System.out.println(categories);

        model.addAttribute("categories", categories);
        return "addpost";
    }

    @PostMapping("/addpost")
    public String addPost(@ModelAttribute Post post) {
        postService.addPost(post);

        return "redirect:/posts";
    }

    @GetMapping({"/posts","/"})
    public String posts(@RequestParam(defaultValue = "-1", required = false) int id, Model model) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("auth" , auth);

        if (id < 0) {
              return "posts";
        } else {
            Post post = postService.getPost((long)id);
            List<Comment> com = commentService.getPostComment((long) id);
            model.addAttribute("post", post);
            model.addAttribute("com", com);
            return "post";
        }
    }

    @ResponseBody
    @GetMapping("/post-add-success")
    public String postAddSuccess() {
        return "Post został pomyślnie dodany!";
    }

    @GetMapping("/edit")
    public String edit(Model model) {
        model.addAttribute("post", new Post());
        List<CategoryEnum> categories =
                new ArrayList<>(Arrays.asList(CategoryEnum.values()));
        System.out.println(categories);

        model.addAttribute("categories", categories);
        return "edit";
    }
}
