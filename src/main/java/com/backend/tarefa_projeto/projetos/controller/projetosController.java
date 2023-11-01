package com.backend.tarefa_projeto.projetos.controller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.backend.tarefa_projeto.projetos.domain.ProjetosDomain;
import com.backend.tarefa_projeto.projetos.requests.ProjetosPostRequestBody;
import com.backend.tarefa_projeto.projetos.requests.ProjetosPutRequestBody;
import com.backend.tarefa_projeto.projetos.service.ProjetoService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/projetos")
public class projetosController {
    
    private final ProjetoService projetoService;

    @Autowired
    public projetosController(ProjetoService projetoService) {
        this.projetoService = projetoService;
    }

    @GetMapping
public ResponseEntity<List<ProjetosDomain>> list() {
    List<ProjetosDomain> activeProjetos = projetoService.listAllActive();
    return ResponseEntity.ok(activeProjetos);
}

    @GetMapping(path = "/{id}")
    public ResponseEntity<ProjetosDomain> findById(@PathVariable Long id) {
        return ResponseEntity.ok(projetoService.findByIdOrThrowBadRequestException(id));
    }

    @PostMapping
    public ResponseEntity<ProjetosDomain> save(@RequestBody ProjetosPostRequestBody projetosPostRequestBody) {
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
