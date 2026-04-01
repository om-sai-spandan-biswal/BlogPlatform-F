package com.om.projects_F.backend.controller;

import com.om.projects_F.backend.dto.LoginDTO;
import com.om.projects_F.backend.dto.LoginResponseDTO;
import com.om.projects_F.backend.dto.SignUpDTO;
import com.om.projects_F.backend.dto.UserDTO;
import com.om.projects_F.backend.security.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService ;

    @PostMapping(path = "/signup")
    public ResponseEntity<UserDTO> signUp(@RequestBody SignUpDTO signUpDTO) {
        UserDTO userDTO = authService.signUp(signUpDTO) ;
        return ResponseEntity.ok(userDTO) ;
    }

    @PostMapping(path = "/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginDTO loginDTO) {
        LoginResponseDTO loginResponseDTO = authService.login(loginDTO) ;
        return ResponseEntity.ok(loginResponseDTO) ;
    }

}
