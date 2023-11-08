package com.backend.tarefa_projeto.controller;

import com.backend.tarefa_projeto.domain.usuario.AuthenticationDTO;
import com.backend.tarefa_projeto.domain.usuario.LoginResponseDTO;
import com.backend.tarefa_projeto.domain.usuario.RegisterDTO;
import com.backend.tarefa_projeto.domain.usuario.UsuarioDTO;
import com.backend.tarefa_projeto.repository.UsuarioRepository;
import com.backend.tarefa_projeto.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UsuarioRepository repository;
    @Autowired
    private TokenService tokenService;


    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data){
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.usuario(), data.senha());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((UsuarioDTO) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDTO data){
        if(this.repository.findByUsuario(data.usuario()) != null) return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.senha());
        UsuarioDTO newUser = new UsuarioDTO(data.usuario(), encryptedPassword);

        this.repository.save(newUser);

        return ResponseEntity.ok().build();
    }
}