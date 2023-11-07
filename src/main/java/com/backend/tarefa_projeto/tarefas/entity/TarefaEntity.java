package com.backend.tarefa_projeto.tarefas.entity;

import com.backend.tarefa_projeto.projetos.entity.ProjetosDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Entity 
@Builder
@NoArgsConstructor
@Data
@Table(name = "tarefas")
public class TarefaEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Removido")
    private boolean removido;

    @Column(name = "descricaoDaTarefa")
    private String descricaoDaTarefa;

    @Column(name = "status")
    private String status;

    @ManyToOne
    @JoinColumn(name = "projeto_id")
    private ProjetosDTO projeto;
}
