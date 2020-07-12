package com.sda.secure.controller;

import com.sda.secure.model.Comment;
import com.sda.secure.model.Post;
import com.sda.secure.model.User;
import com.sda.secure.service.CommentService;
import com.sda.secure.service.PostService;
import com.sda.secure.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class CommentController {

    PostService postService;
    CommentService commentService;
    UserService userService;

    public CommentController(PostService postService, CommentService commentService, UserService userService) {
        this.postService = postService;
        this.commentService = commentService;
        this.userService = userService;
    }

    @GetMapping("/addcomment/{post_id}")
    public String getFormForComments(@PathVariable int post_id, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("auth" , auth);
        Post post = postService.getPost((long)post_id);
        model.addAttribute("post", post);
        Comment comment = new Comment();
        model.addAttribute("comment", comment);
        return "addcomment";

    }
@ResponseBody
    @PostMapping("/addcomment/{post_id}")
    public String addComment(@PathVariable int post_id, Comment comment) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
//        model.addAttribute("auth" , auth);
//        Post post = postService.getPost((long)post_id);
//        model.addAttribute("post", post);


        commentService.addComment((long)post_id, user.getId(), comment);
        return "Dodałem pomyślnie komentarz!";

    }
}
