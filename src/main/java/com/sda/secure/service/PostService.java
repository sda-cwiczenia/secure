package com.sda.secure.service;

import com.sda.secure.model.Post;
import com.sda.secure.model.User;

import com.sda.secure.repository.PostRepository;
import com.sda.secure.repository.RoleRepository;
import com.sda.secure.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    UserRepository userRepository;
    PostRepository postRepository;
    RoleRepository roleRepository;


    /*CommentRepository commentRepository;*/

    /*  public String addComment(Long post_id, Long user_id, String message) {
          if (postRepository.findById(post_id).isPresent() && userRepository.findById(user_id).isPresent()) {
              Post post = postRepository.getOne(post_id);
              User user = userRepository.getOne(user_id);
              Comment comment = new Comment(message, user, post);
              commentRepository.save(comment);
              return "dodano komentarz";
          }
          return "błędne id posta lub usera";
      }
      public void deleteComment(Long comment_id, Long user_id){
          if(userRepository.findById(user_id).isPresent() && commentRepository.findById(comment_id).isPresent()) {
              User user = userRepository.getOne(user_id);
              Comment comment = commentRepository.getOne(comment_id);
              if (comment.getUser() == user) {
                  commentRepository.delete(comment);
              }
          }
      }*/

    @Autowired
    public PostService(UserRepository userRepository, PostRepository postRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
        this.roleRepository = roleRepository;
    }

    public List<Post> getPosts() {
        return postRepository.findAll();

    }

    public Post getPost(Long post_id){
        if(postRepository.findById(post_id).isPresent()){
            return postRepository.getOne(post_id);
        }
        return null;
    }

    public void addPost(Post post) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        post.setUser(userRepository.findByEmail(auth.getName()));
        postRepository.save(post);
    }

    public void removePost(Long user_id, Long post_id) {
        if(userRepository.findById(user_id).isPresent() && postRepository.findById(post_id).isPresent()) {
            Post post = postRepository.getOne(post_id);
            User user = userRepository.getOne(user_id);
            if (user.getRoles().contains(roleRepository.getOne(2L)) || post.getUser().getId().equals(user.getId()))
                postRepository.delete(post);
        }
    }

    public void updatePost(Long post_id, Post updatedPost){
        Post post = postRepository.getOne(post_id);

        post.setTitle(updatedPost.getTitle());
        post.setContent(updatedPost.getContent());
        post.setCategoryEnum(updatedPost.getCategoryEnum());
        postRepository.save(post);
    }
    /*
    public String changeTitle(Long user_id, Long post_id, String newTitle){
        if (userRepository.findById(user_id).isPresent()){
            if (userRepository.getOne(user_id).getRoles().contains(roleRepository.getOne(2L))){
                Post post = postRepository.findFirstByTitle(postRepository.getOne(post_id).getTitle());
                post.setTitle(newTitle);
                postRepository.save(post);
                return "zmodyfikowano tytuł posta";
            }
            return "brak uprawnień";
        }
    return "błędne dane";
    }*/

}

