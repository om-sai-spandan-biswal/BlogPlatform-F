package com.om.projects_F.backend.mapper;

import com.om.projects_F.backend.dto.CommentDTO;
import com.om.projects_F.backend.entity.Comment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    Comment toEntity(CommentDTO commentDTO) ;
    CommentDTO toDTO(Comment comment) ;
}
