package com.backend.tarefa_projeto.projetos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import com.backend.tarefa_projeto.projetos.domain.ProjetosDomain;

public interface ProjetosRepository extends JpaRepository<ProjetosDomain, Long> {
    List<ProjetosDomain> findByNomeDoProjeto(String nomeDoProjeto);
}
