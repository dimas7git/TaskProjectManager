package com.backend.tarefa_projeto.projetos.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.backend.tarefa_projeto.projetos.domain.ProjetosDomain;
import com.backend.tarefa_projeto.projetos.exception.BadRequestException;
import com.backend.tarefa_projeto.projetos.repository.ProjetosRepository;
import com.backend.tarefa_projeto.projetos.requests.ProjetosPostRequestBody;
import com.backend.tarefa_projeto.projetos.requests.ProjetosPutRequestBody;

import jakarta.transaction.Transactional;

@Service
public class ProjetoService {
    private final ProjetosRepository projetosRepository;

    @Autowired
    public ProjetoService(ProjetosRepository projetosRepository) {
        this.projetosRepository = projetosRepository;
    }

    public List<ProjetosDomain> listAllActive() {
        List<ProjetosDomain> allProjetos = projetosRepository.findAll();
        List<ProjetosDomain> activeProjetos = new ArrayList<>();
    
        for (ProjetosDomain projeto : allProjetos) {
            if (!projeto.isRemovido()) {
                activeProjetos.add(projeto);
            }
        }
    
        return activeProjetos;
    }

    public ProjetosDomain findByIdOrThrowBadRequestException(Long id) {
        return projetosRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Projeto not found"));
    }

    public List<ProjetosDomain> findByNomeDoProjeto(String name) {
        return projetosRepository.findByNomeDoProjeto(name);
    }

    public ProjetosDomain save(ProjetosPostRequestBody projetosPostRequestBody) {
        ProjetosDomain projeto = ProjetosDomain.builder()
                .nomeDoProjeto(projetosPostRequestBody.getNomeDoProjeto())
                .dataDeInicio(projetosPostRequestBody.getDataDeInicio())
                .custoDoProjeto(projetosPostRequestBody.getCustoDoProjeto())    
                .build();
        return projetosRepository.save(projeto);
    }

    @Transactional
    public boolean softDeleteProjeto(Long id) {
        Optional<ProjetosDomain> projetoOptional = projetosRepository.findById(id);
        if (projetoOptional.isPresent()) {
            ProjetosDomain projeto = projetoOptional.get();
            projeto.setRemovido(true);
            projetosRepository.save(projeto);
            return true;
        }
        return false;
    }

    public void replace(ProjetosPutRequestBody projetosPutRequestBody) {
        ProjetosDomain projeto = findByIdOrThrowBadRequestException(projetosPutRequestBody.getId());
        projeto.setNomeDoProjeto(projetosPutRequestBody.getNomeDoProjeto());
        projeto.setDataDeInicio(projetosPutRequestBody.getDataDeInicio());
        projeto.setCustoDoProjeto(projetosPutRequestBody.getCustoDoProjeto());
        projetosRepository.save(projeto);
    }
}

