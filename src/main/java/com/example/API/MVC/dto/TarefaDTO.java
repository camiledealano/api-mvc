package com.example.API.MVC.dto;

import com.example.API.MVC.model.enums.Prioridade;
import com.example.API.MVC.model.enums.Status;
import lombok.Data;

import java.time.LocalDate;

@Data
public class TarefaDTO {
    private Long id;
    private String titulo;
    private String descricao;
    private Prioridade prioridade;
    private Status status;
    private LocalDate dataLimite;
}
