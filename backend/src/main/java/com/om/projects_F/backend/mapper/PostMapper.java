package com.om.projects_F.backend.mapper;

import com.om.projects_F.backend.dto.PostDTO;
import com.om.projects_F.backend.entity.Post;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PostMapper {
    Post toEntity(PostDTO postDTO) ;
    PostDTO toDTO(Post post) ;
}
