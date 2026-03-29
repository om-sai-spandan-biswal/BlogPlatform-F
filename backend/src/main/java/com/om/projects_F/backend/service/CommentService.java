package com.om.projects_F.backend.service;


import com.om.projects_F.backend.dto.CommentDTO;
import com.om.projects_F.backend.entity.Comment;
import com.om.projects_F.backend.entity.Post;
import com.om.projects_F.backend.exception.ResourceNotFoundException;
import com.om.projects_F.backend.mapper.CommentMapper;
import com.om.projects_F.backend.repository.CommentRepository;
import com.om.projects_F.backend.repository.PostRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository ;
    private final PostRepository postRepository ;
    private final CommentMapper commentMapper ;

    public List<CommentDTO> getCommentsByPostId(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("There is No post with Id : " + postId)) ;
        List<Comment> comments = commentRepository.findCommentByPost(post) ;
        return comments.stream()
                .map(commentMapper::toDTO)
                .toList() ;
    }

    @Transactional
    public CommentDTO commentAPostById(Long postId, CommentDTO commentDTO) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("There is No post with Id : " + postId)) ;
        Comment comment = commentMapper.toEntity(commentDTO) ;
        comment.setPost(post);
        commentRepository.save(comment) ;
        return commentMapper.toDTO(comment) ;
    }


}
