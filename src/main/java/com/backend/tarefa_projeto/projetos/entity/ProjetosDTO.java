package com.backend.tarefa_projeto.projetos.entity;

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
    private Long id;

    @Column(name = "Removido") 
    private boolean removido;

    @Column(name = "nomeDoProjeto")
    private String nomeDoProjeto;
    
    @Column(name = "dataDeInicio")
    private LocalDate dataDeInicio;
    
    @Column(name = "custoDoProjeto")
    private BigDecimal custoDoProjeto;

}
