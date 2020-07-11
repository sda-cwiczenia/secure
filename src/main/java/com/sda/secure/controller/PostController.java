package com.sda.secure.controller;

import com.sda.secure.model.CategoryEnum;
import com.sda.secure.model.Post;
import com.sda.secure.service.CommentService;
import com.sda.secure.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
    public String posts() {
        return "posts";
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
