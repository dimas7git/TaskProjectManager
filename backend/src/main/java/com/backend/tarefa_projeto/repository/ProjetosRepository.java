package com.backend.tarefa_projeto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.tarefa_projeto.domain.projetos.ProjetosDTO;

import java.util.List;

public interface ProjetosRepository extends JpaRepository<ProjetosDTO, Long> {
    List<ProjetosDTO> findByNomeDoProjeto(String nomeDoProjeto);
}
