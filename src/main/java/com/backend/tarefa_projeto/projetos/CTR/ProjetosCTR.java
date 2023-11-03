package com.backend.tarefa_projeto.projetos.CTR;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.tarefa_projeto.projetos.DAO.ProjetoDAO;
import com.backend.tarefa_projeto.projetos.DTO.ProjetosDTO;
import com.backend.tarefa_projeto.projetos.DTO.ProjetosPostRequestBody;
import com.backend.tarefa_projeto.projetos.DTO.ProjetosPutRequestBody;

@RestController
@RequestMapping("/projetos")
public class ProjetosCTR {
    
    private final ProjetoDAO projetoService;

    @Autowired
    public ProjetosCTR(ProjetoDAO projetoService) {
        this.projetoService = projetoService;
    }

    @GetMapping
    public ResponseEntity<List<ProjetosDTO>> list() {
    List<ProjetosDTO> activeProjetos = projetoService.listAllActive();
        return ResponseEntity.ok(activeProjetos);
}

    @GetMapping(path = "/{id}")
    public ResponseEntity<ProjetosDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(projetoService.findByIdOrThrowBadRequestException(id));
    }

    @PostMapping
    public ResponseEntity<ProjetosDTO> save(@RequestBody ProjetosPostRequestBody projetosPostRequestBody) {
        return new ResponseEntity<>(projetoService.save(projetosPostRequestBody), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        projetoService.softDeleteProjeto(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public ResponseEntity<Void> replace(@RequestBody ProjetosPutRequestBody projetosPutRequestBody) {
        projetoService.replace(projetosPutRequestBody);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
