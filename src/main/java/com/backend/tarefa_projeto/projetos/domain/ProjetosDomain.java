package com.backend.tarefa_projeto.projetos.domain;

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
public class ProjetosDomain {

    
    @Id // Anotação para indicar que este campo é a chave primária
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Removido") // Mapeie o campo "Removido" para a coluna com o mesmo nome no banco de dados
    private boolean removido;

    @Column(name = "nomeDoProjeto")
    private String nomeDoProjeto;
    
    @Column(name = "dataDeInicio")
    private LocalDate dataDeInicio;
    
    @Column(name = "custoDoProjeto")
    private BigDecimal custoDoProjeto;

}
