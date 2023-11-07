package com.backend.tarefa_projeto.tarefas.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.backend.tarefa_projeto.projetos.entity.ProjetosDTO;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TarefaPostRequestBody {

    private String descricaoDaTarefa;
    private String status;
    private ProjetosDTO projeto;

}
