package com.backend.tarefa_projeto.tarefas.services;
import com.backend.tarefa_projeto.tarefas.entity.TarefaEntity;
import com.backend.tarefa_projeto.tarefas.entity.TarefaPostRequestBody;
import com.backend.tarefa_projeto.tarefas.entity.TarefaPutRequestBody;
import com.backend.tarefa_projeto.projetos.exception.BadRequestException;
import com.backend.tarefa_projeto.tarefas.repository.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TarefaService {

    private final TarefaRepository tarefaRepository;

    @Autowired
    public TarefaService(TarefaRepository tarefaRepository) {
        this.tarefaRepository = tarefaRepository;
    }

    public List<TarefaEntity> listAllActive() {
        List<TarefaEntity> allTarefas = tarefaRepository.findAll();
        List<TarefaEntity> activeTarefas = new ArrayList<>();

        for (TarefaEntity tarefa : allTarefas) {
            if (!tarefa.isRemovido()) {
                activeTarefas.add(tarefa);
            }
        }

        return activeTarefas;
    }

    public TarefaEntity findByIdOrThrowBadRequestException(Long id) {
        return tarefaRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Tarefa not found"));
    }

    public TarefaEntity save(TarefaPostRequestBody tarefaPostRequestBody) {
        TarefaEntity tarefa = TarefaEntity.builder()
                .descricaoDaTarefa(tarefaPostRequestBody.getDescricaoDaTarefa())
                .status(tarefaPostRequestBody.getStatus())
                .projeto(tarefaPostRequestBody.getProjeto())
                .build();
        return tarefaRepository.save(tarefa);
    }

    @Transactional
    public boolean softDeleteTarefa(Long id) {
        Optional<TarefaEntity> tarefaOptional = tarefaRepository.findById(id);
        if (tarefaOptional.isPresent()) {
            TarefaEntity tarefa = tarefaOptional.get();
            tarefa.setRemovido(true);
            tarefaRepository.save(tarefa);
            return true;
        }
        return false;
    }

    public void replace(TarefaPutRequestBody tarefaPutRequestBody) {
        TarefaEntity tarefa = findByIdOrThrowBadRequestException(tarefaPutRequestBody.getId());
        tarefa.setDescricaoDaTarefa(tarefaPutRequestBody.getDescricaoDaTarefa());
        tarefa.setStatus(tarefaPutRequestBody.getStatus());
        tarefa.setProjeto(tarefaPutRequestBody.getProjeto());
        tarefaRepository.save(tarefa);
    }
}