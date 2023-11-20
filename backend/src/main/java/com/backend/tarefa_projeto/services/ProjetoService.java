package com.backend.tarefa_projeto.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.backend.tarefa_projeto.exception.BadRequestException;
import com.backend.tarefa_projeto.repository.ProjetosRepository;
import org.springframework.stereotype.Service;

import com.backend.tarefa_projeto.domain.projetos.ProjetosDTO;
import com.backend.tarefa_projeto.domain.projetos.ProjetosPostRequestBody;
import com.backend.tarefa_projeto.domain.projetos.ProjetosPutRequestBody;

import jakarta.transaction.Transactional;

@Service
public class ProjetoService {
    private final ProjetosRepository projetosRepository;

    public ProjetoService(ProjetosRepository projetosRepository) {
        this.projetosRepository = projetosRepository;
    }

    public List<ProjetosDTO> listAllActive() {
        List<ProjetosDTO> allProjetos = projetosRepository.findAll();
        List<ProjetosDTO> activeProjetos = new ArrayList<>();
    
        for (ProjetosDTO projeto : allProjetos) {
            //if (!projeto.isRemovido()) {
                activeProjetos.add(projeto);
            //}
        }
    
        return activeProjetos;
    }

    public ProjetosDTO findByIdOrThrowBadRequestException(Long id) {
        return projetosRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Projeto not found"));
    }
    
    public ProjetosDTO save(ProjetosPostRequestBody projetosPostRequestBody) {
        ProjetosDTO projeto = ProjetosDTO.builder()
                .nomeDoProjeto(projetosPostRequestBody.getNomeDoProjeto())
                .dataDeInicio(projetosPostRequestBody.getDataDeInicio())
                .custoDoProjeto(projetosPostRequestBody.getCustoDoProjeto())    
                .build();
        return projetosRepository.save(projeto);
    }

    @Transactional
    public boolean softDeleteProjeto(Long id) {
        Optional<ProjetosDTO> projetoOptional = projetosRepository.findById(id);
        if (projetoOptional.isPresent()) {
            ProjetosDTO projeto = projetoOptional.get();
            projeto.setRemovido(true);
            projetosRepository.save(projeto);
            return true;
        }
        return false;
    }

    public void replace(ProjetosPutRequestBody projetosPutRequestBody) {
        ProjetosDTO projeto = findByIdOrThrowBadRequestException(projetosPutRequestBody.getId());
        projeto.setNomeDoProjeto(projetosPutRequestBody.getNomeDoProjeto());
        projeto.setDataDeInicio(projetosPutRequestBody.getDataDeInicio());
        projeto.setCustoDoProjeto(projetosPutRequestBody.getCustoDoProjeto());

        if (projetosPutRequestBody.isRemovido() != projeto.isRemovido()) {
            projeto.setRemovido(projetosPutRequestBody.isRemovido());
        }
        projetosRepository.save(projeto);
    }
}

