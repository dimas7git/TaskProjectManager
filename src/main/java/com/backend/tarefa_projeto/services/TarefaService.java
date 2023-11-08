package com.backend.tarefa_projeto.services;
import com.backend.tarefa_projeto.domain.tarefas.TarefaDTO;
import com.backend.tarefa_projeto.domain.tarefas.TarefaPostRequestBody;
import com.backend.tarefa_projeto.domain.tarefas.TarefaPutRequestBody;
import com.backend.tarefa_projeto.exception.BadRequestException;
import com.backend.tarefa_projeto.repository.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TarefaService {

    private final TarefaRepository tarefaRepository;

    public TarefaService(TarefaRepository tarefaRepository) {
        this.tarefaRepository = tarefaRepository;
    }

    public List<TarefaDTO> listAllActive() {
        List<TarefaDTO> allTarefas = tarefaRepository.findAll();
        List<TarefaDTO> activeTarefas = new ArrayList<>();

        for (TarefaDTO tarefa : allTarefas) {
            if (!tarefa.isRemovido()) {
                activeTarefas.add(tarefa);
            }
        }

        return activeTarefas;
    }

    public TarefaDTO findByIdOrThrowBadRequestException(Long id) {
        return tarefaRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Tarefa not found"));
    }

    public TarefaDTO save(TarefaPostRequestBody tarefaPostRequestBody) {
        TarefaDTO tarefa = TarefaDTO.builder()
                .descricaoDaTarefa(tarefaPostRequestBody.getDescricaoDaTarefa())
                .status(tarefaPostRequestBody.getStatus())
                .projeto(tarefaPostRequestBody.getProjeto())
                .build();
                System.out.println(tarefaPostRequestBody.getProjeto());
        return tarefaRepository.save(tarefa);
    }

    @Transactional
    public boolean softDeleteTarefa(Long id) {
        Optional<TarefaDTO> tarefaOptional = tarefaRepository.findById(id);
        if (tarefaOptional.isPresent()) {
            TarefaDTO tarefa = tarefaOptional.get();
            tarefa.setRemovido(true);
            tarefaRepository.save(tarefa);
            return true;
        }
        return false;
    }

    public void replace(TarefaPutRequestBody tarefaPutRequestBody) {
        TarefaDTO tarefa = findByIdOrThrowBadRequestException(tarefaPutRequestBody.getId());
        tarefa.setDescricaoDaTarefa(tarefaPutRequestBody.getDescricaoDaTarefa());
        tarefa.setStatus(tarefaPutRequestBody.getStatus());
        tarefa.setProjeto(tarefaPutRequestBody.getProjeto());
        tarefaRepository.save(tarefa);
    }
}