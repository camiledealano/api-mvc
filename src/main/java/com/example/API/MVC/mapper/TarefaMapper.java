package com.example.API.MVC.mapper;

import com.example.API.MVC.dto.TarefaDTO;
import com.example.API.MVC.dto.TarefaRequestDTO;
import com.example.API.MVC.model.Tarefa;
import org.springframework.stereotype.Component;

@Component
public class TarefaMapper {

    public TarefaDTO toDTO(Tarefa tarefa) {
        if (tarefa == null) {
            return null;
        }
        TarefaDTO dto = new TarefaDTO();
        dto.setId(tarefa.getId());
        dto.setTitulo(tarefa.getTitulo());
        dto.setDescricao(tarefa.getDescricao());
        dto.setPrioridade(tarefa.getPrioridade());
        dto.setStatus(tarefa.getStatus());
        dto.setDataLimite(tarefa.getDataLimite());
        return dto;
    }

    public Tarefa toEntity(TarefaRequestDTO dto) {
        if (dto == null) {
            return null;
        }
        Tarefa tarefa = new Tarefa();
        tarefa.setTitulo(dto.getTitulo());
        tarefa.setDescricao(dto.getDescricao());
        tarefa.setPrioridade(dto.getPrioridade());
        tarefa.setStatus(dto.getStatus());
        tarefa.setDataLimite(dto.getDataLimite());
        return tarefa;
    }

    public void updateEntityFromDTO(TarefaRequestDTO dto, Tarefa tarefa) {
        if (dto == null || tarefa == null) {
            return;
        }
        tarefa.setTitulo(dto.getTitulo());
        tarefa.setDescricao(dto.getDescricao());
        tarefa.setPrioridade(dto.getPrioridade());
        tarefa.setStatus(dto.getStatus());
        tarefa.setDataLimite(dto.getDataLimite());
    }
}