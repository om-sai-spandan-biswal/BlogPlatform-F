package com.om.projects_F.backend.service;

import com.om.projects_F.backend.dto.DeleteResponseDTO;
import com.om.projects_F.backend.dto.PostDTO;
import com.om.projects_F.backend.entity.Post;
import com.om.projects_F.backend.exception.ResourceNotFoundException;
import com.om.projects_F.backend.mapper.PostMapper;
import com.om.projects_F.backend.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository ;
    private final PostMapper postMapper ;

    public PostDTO getPostById(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("There is No post with id : " + id)) ;
        return postMapper.toDTO(post) ;
    }

    public List<PostDTO> getAllPost() {
        List<Post> posts = postRepository.findAll() ;
        return posts.stream()
                .map(postMapper::toDTO)
                .toList() ;
    }

    public PostDTO createPost(PostDTO postDTO) {
        Post post = postMapper.toEntity(postDTO) ;
        Post createdPost = postRepository.save(post) ;
        return postMapper.toDTO(createdPost) ;
    }

    public PostDTO updatePost(Long id, PostDTO postDTO) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("There is No post with id : " + id));
        post.setTitle(postDTO.getTitle());
        post.setContent(postDTO.getContent());
        Post updatedPost = postRepository.saveAndFlush(post) ;
        return postMapper.toDTO(updatedPost);
    }


    public DeleteResponseDTO deleteById(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("There is No post with id : " + id)) ;
        postRepository.deleteById(id);
        return new  DeleteResponseDTO("Post Deleted Successfully") ;
    }
}
