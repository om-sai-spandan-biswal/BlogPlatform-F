package com.om.projects_F.backend.repository;

import com.om.projects_F.backend.entity.Comment;
import com.om.projects_F.backend.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {
    List<Comment> findCommentByPost(Post post) ;
}
