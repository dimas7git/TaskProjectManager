package com.backend.tarefa_projeto.domain.projetos;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.Data;

@Data
public class ProjetosPutRequestBody {
    private Long id;
    private String nomeDoProjeto;
    private LocalDate dataDeInicio;
    private BigDecimal custoDoProjeto;
    private boolean removido; 
}
