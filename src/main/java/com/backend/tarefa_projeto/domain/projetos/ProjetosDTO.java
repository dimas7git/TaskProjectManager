package com.backend.tarefa_projeto.domain.projetos;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Entity 
@Builder
@NoArgsConstructor
@Table(name="projetos")
public class ProjetosDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_projeto")
    private Long id;

    private boolean removido;

    @Column(name = "nome_do_projeto")
    private String nomeDoProjeto;
    
    @Column(name = "data_de_inicio")
    private LocalDate dataDeInicio;
    
    @Column(name = "custo_do_projeto")
    private BigDecimal custoDoProjeto;
}
