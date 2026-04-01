package com.om.projects_F.backend.controller;

import com.om.projects_F.backend.dto.CommentDTO;
import com.om.projects_F.backend.dto.DeleteResponseDTO;
import com.om.projects_F.backend.dto.PostDTO;
import com.om.projects_F.backend.service.CommentService;
import com.om.projects_F.backend.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostController {
    private final PostService postService ;
    private final CommentService commentService ;

    @GetMapping(path = "/{postId}")
    public ResponseEntity<PostDTO> getPostById(@PathVariable(name = "postId") Long id) {
        PostDTO postDTO = postService.getPostById(id) ;
        return ResponseEntity.ok(postDTO) ;
    }

    @GetMapping
    public ResponseEntity<List<PostDTO>> getAllPost() {
        List<PostDTO> postDTOList = postService.getAllPost() ;
        return ResponseEntity.ok(postDTOList) ;
    }

    @PostMapping
    public ResponseEntity<PostDTO> createPost(@RequestBody PostDTO postDTO) {
        PostDTO createdPost = postService.createPost(postDTO) ;
        return new ResponseEntity<>(createdPost, HttpStatus.CREATED) ;
    }

    @PutMapping(path = "/{postId}")
    public ResponseEntity<PostDTO> updatePost(@PathVariable(name = "postId") Long id, @RequestBody PostDTO postDTO) {
        PostDTO updatePost = postService.updatePost(id, postDTO) ;
        return ResponseEntity.ok(postDTO) ;
    }

    @DeleteMapping(path = "/{postId}")
    public ResponseEntity<DeleteResponseDTO> deletePost(@PathVariable(name = "postId") Long id) {
        DeleteResponseDTO deleteResponseDTO = postService.deleteById(id) ;
        return ResponseEntity.ok(deleteResponseDTO) ;
    }

    @GetMapping(path = "/{postId}/comments")
    public ResponseEntity<List<CommentDTO>> getCommentsByPostId(@PathVariable(name = "postId") Long id) {
        List<CommentDTO> commentDTOList = commentService.getCommentsByPostId(id) ;
        return ResponseEntity.ok(commentDTOList) ;
    }

    @PostMapping("/{postId}")
    public ResponseEntity<CommentDTO> commentAPostById(@PathVariable(name = "postId") Long id, @RequestBody CommentDTO commentDTO) {
        CommentDTO comment = commentService.commentAPostById(id,commentDTO) ;
        return ResponseEntity.ok(comment) ;
    }
}
