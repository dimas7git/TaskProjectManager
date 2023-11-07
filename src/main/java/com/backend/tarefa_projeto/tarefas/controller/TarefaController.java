package com.backend.tarefa_projeto.tarefas.controller;
import com.backend.tarefa_projeto.tarefas.entity.TarefaEntity;
import com.backend.tarefa_projeto.tarefas.entity.TarefaPostRequestBody;
import com.backend.tarefa_projeto.tarefas.entity.TarefaPutRequestBody;
import com.backend.tarefa_projeto.tarefas.services.TarefaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tarefas")
public class TarefaController{

    private final TarefaService tarefaService;

    @Autowired
    public TarefaController(TarefaService tarefaService) {
        this.tarefaService = tarefaService;
    }

    @GetMapping
    public ResponseEntity<List<TarefaEntity>> list() {
        List<TarefaEntity> activeTarefas = tarefaService.listAllActive();
        return ResponseEntity.ok(activeTarefas);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<TarefaEntity> findById(@PathVariable Long id) {
        return ResponseEntity.ok(tarefaService.findByIdOrThrowBadRequestException(id));
    }

    @PostMapping
    public ResponseEntity<TarefaEntity> save(@RequestBody TarefaPostRequestBody tarefaPostRequestBody) {
        return new ResponseEntity<>(tarefaService.save(tarefaPostRequestBody), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        tarefaService.softDeleteTarefa(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public ResponseEntity<Void> replace(@RequestBody TarefaPutRequestBody tarefaPutRequestBody) {
        tarefaService.replace(tarefaPutRequestBody);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}