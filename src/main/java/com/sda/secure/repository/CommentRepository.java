package com.sda.secure.repository;

import com.sda.secure.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface CommentRepository extends JpaRepository<Comment,Long> {
    List<Comment> findAllByPostId(Long id);
}
