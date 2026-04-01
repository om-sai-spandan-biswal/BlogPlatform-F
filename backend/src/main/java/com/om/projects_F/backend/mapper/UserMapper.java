package com.om.projects_F.backend.mapper;

import com.om.projects_F.backend.dto.SignUpDTO;
import com.om.projects_F.backend.dto.UserDTO;
import com.om.projects_F.backend.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toEntity(SignUpDTO signUpDTO) ;
    UserDTO toDTO(User user) ;
}
