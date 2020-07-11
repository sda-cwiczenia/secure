package com.sda.secure.service;

import com.sda.secure.model.Comment;
import com.sda.secure.model.Post;
import com.sda.secure.model.User;
import com.sda.secure.repository.CommentRepository;
import com.sda.secure.repository.PostRepository;
import com.sda.secure.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    CommentRepository commentRepository;
    PostRepository postRepository;
    UserRepository userRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository, PostRepository postRepository, UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }


    public List<Comment> getPostComment(Long post_id) {
        List<Comment> allByPostId = commentRepository.findAllByPostId(post_id);
        // if(allByPostId!=null) return allByPostId;
        // return new ArrayList<Comment>();
        return allByPostId;
    }

    public String addComment(Long post_id, Long user_id, Comment comment) {
        if (postRepository.findById(post_id).isPresent() && userRepository.findById(user_id).isPresent()) {
            Post post = postRepository.getOne(post_id);
            User user = userRepository.getOne(user_id);
            comment.setPost(post);
            comment.setUser(user);
            commentRepository.save(comment);
            return "dodano komentarz";
        }
        return "błędne id posta lub usera";


    }
    /*
    public void deleteComment(Long comment_id, Long user_id){
        if(userRepository.findById(user_id).isPresent() && commentRepository.findById(comment_id).isPresent()) {
            User user = userRepository.getOne(user_id);
            Comment comment = commentRepository.getOne(comment_id);
            if (comment.getUser() == user) {
                commentRepository.delete(comment);
            }
        }
    }*/
}

