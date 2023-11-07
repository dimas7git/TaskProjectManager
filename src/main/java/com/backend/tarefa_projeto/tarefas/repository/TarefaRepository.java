package com.backend.tarefa_projeto.tarefas.repository;
import com.backend.tarefa_projeto.tarefas.entity.TarefaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface TarefaRepository extends JpaRepository<TarefaEntity, Long> {
    List<TarefaEntity> findByDescricaoDaTarefa(String descricaoDaTarefa);
}
