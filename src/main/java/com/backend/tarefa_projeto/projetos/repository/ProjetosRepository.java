package com.backend.tarefa_projeto.projetos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.tarefa_projeto.projetos.DTO.ProjetosDTO;

import java.util.List;

public interface ProjetosRepository extends JpaRepository<ProjetosDTO, Long> {
    List<ProjetosDTO> findByNomeDoProjeto(String nomeDoProjeto);
}
