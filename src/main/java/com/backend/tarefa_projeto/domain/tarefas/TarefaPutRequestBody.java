package com.backend.tarefa_projeto.domain.tarefas;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.backend.tarefa_projeto.domain.projetos.ProjetosDTO;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TarefaPutRequestBody {

    private Long id; 
    private String descricaoDaTarefa;
    private String status;
    private ProjetosDTO projeto;

}