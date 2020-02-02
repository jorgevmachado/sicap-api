package br.com.jorgevmachado.sicapapi.controllers;

import br.com.jorgevmachado.sicapapi.dto.EmailDTO;
import br.com.jorgevmachado.sicapapi.securities.JWTUtil;
import br.com.jorgevmachado.sicapapi.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import br.com.jorgevmachado.sicapapi.securities.UserSpringSecurity;
import br.com.jorgevmachado.sicapapi.services.UserService;

@RestController
@RequestMapping(value="/api/auth")
public class AuthController {

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private AuthService service;

    @PostMapping(value = "/refresh-token")
    public ResponseEntity<?> refreshToken(HttpServletResponse response) {
        UserSpringSecurity user = UserService.authenticated();
        if (user != null) {
            String token = jwtUtil.generateToken(user.getUsername());
            response.addHeader("Authorization", "Bearer " + token);
            response.addHeader("access-control-expose-headers", "Authorization");
            return ResponseEntity.ok().body("token: " + token);
        }
        return null;
    }

    @PostMapping(value = "forgot")
    public ResponseEntity<Void> forgot(@Valid @RequestBody EmailDTO emailDTO) {
        service.sendNewPassword(emailDTO.getEmail());
        return ResponseEntity.noContent().build();
    }

}
