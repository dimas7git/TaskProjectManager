package com.backend.tarefa_projeto.repository;
import com.backend.tarefa_projeto.domain.tarefas.TarefaDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface TarefaRepository extends JpaRepository<TarefaDTO, Long> {
    List<TarefaDTO> findByDescricaoDaTarefa(String descricaoDaTarefa);
}
