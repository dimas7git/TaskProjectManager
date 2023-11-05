package com.backend.tarefa_projeto.tarefas.domain;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.backend.tarefa_projeto.projetos.entity.ProjetosDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Entity 
@Builder
@Table(name="tarefas")
public class tarefasDomain {
    
    @Id // Anotação para indicar que este campo é a chave primária
    private Long id;

    @Column(name = "Removido") // Mapeie o campo "Removido" para a coluna com o mesmo nome no banco de dados
    private boolean removido;

    @Column(name = "Descricao_da_Tarefa")
    private String Descricao_da_Tarefa;

    @Column(name = "Data_de_Conclusao")
    private LocalDate Data_de_Conclusao;

    @Column(name = "custoDoProjeto")
    private BigDecimal custoDoProjeto;

    @ManyToOne
    @JoinColumn(name = "Projeto_id")
    private ProjetosDTO projeto;
}
