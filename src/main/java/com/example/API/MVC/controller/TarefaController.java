package com.example.API.MVC.controller;

import com.example.API.MVC.model.Tarefa;
import com.example.API.MVC.repository.TarefaRepository;
import com.example.API.MVC.dto.TarefaDTO;
import com.example.API.MVC.dto.TarefaRequestDTO;
import com.example.API.MVC.mapper.TarefaMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/tarefas")
@AllArgsConstructor
public class TarefaController {

    private final TarefaRepository repository;
    private final TarefaMapper mapper;

    @GetMapping
    public List<TarefaDTO> listar() {
        return repository.findAll().stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TarefaDTO> buscarPorId(@PathVariable Long id) {
        return repository.findById(id)
                .map(mapper::toDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public TarefaDTO criar(@RequestBody TarefaRequestDTO tarefaRequestDTO) {
        Tarefa tarefa = mapper.toEntity(tarefaRequestDTO);
        Tarefa savedTarefa = repository.save(tarefa);
        return mapper.toDTO(savedTarefa);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TarefaDTO> atualizar(@PathVariable Long id, @RequestBody TarefaRequestDTO tarefaRequestDTO) {
        return repository.findById(id)
                .map(tarefaExistente -> {
                    mapper.updateEntityFromDTO(tarefaRequestDTO, tarefaExistente);
                    Tarefa atualizado = repository.save(tarefaExistente);
                    return ResponseEntity.ok(mapper.toDTO(atualizado));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        return repository.findById(id)
                .map(tarefa -> {
                    repository.delete(tarefa);
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}