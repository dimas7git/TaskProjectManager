package com.backend.tarefa_projeto.repository;

import com.backend.tarefa_projeto.domain.usuario.UsuarioDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioRepository extends JpaRepository<UsuarioDTO, Long> {
    UserDetails findByUsuario(String username);
}