package com.om.projects_F.backend.security;

import com.om.projects_F.backend.dto.LoginDTO;
import com.om.projects_F.backend.dto.LoginResponseDTO;
import com.om.projects_F.backend.dto.SignUpDTO;
import com.om.projects_F.backend.dto.UserDTO;
import com.om.projects_F.backend.entity.User;
import com.om.projects_F.backend.entity.enums.Role;
import com.om.projects_F.backend.exception.ResourceNotFoundException;
import com.om.projects_F.backend.mapper.UserMapper;
import com.om.projects_F.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository ;
    private final UserMapper userMapper ;
    private final PasswordEncoder passwordEncoder ;
    private final AuthenticationManager authenticationManager ;
    private final JWTService jwtService ;

    public UserDTO signUp(SignUpDTO signUpDTO) {

        User user = userRepository.findUserByEmail(signUpDTO.getEmail()).orElse(null) ;
        if(user != null) throw new RuntimeException("User with email : " + signUpDTO.getEmail() + " is Already Exist") ;

        User newUser = userMapper.toEntity(signUpDTO) ;
        newUser.setRole(Set.of(Role.USER));
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));

        newUser = userRepository.save(newUser) ;

        return userMapper.toDTO(newUser) ;
    }

    public LoginResponseDTO login(LoginDTO loginDTO) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDTO.getEmail(),loginDTO.getPassword()
        )) ;

        User user = (User) authentication.getPrincipal();
        String accessToken = jwtService.generateAccessToken(user);

         return LoginResponseDTO.builder()
                 .accessToken(accessToken)
                 .build();
    }



}
