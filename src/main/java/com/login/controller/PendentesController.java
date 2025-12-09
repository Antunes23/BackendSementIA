package com.login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.login.entities.Pendentes;
import com.login.service.PendentesService;
import java.util.List;

@RestController
@RequestMapping("/pendentes") // URL base
@CrossOrigin(origins = "*") // Permite acesso do Next.js
public class PendentesController {

    @Autowired
    private PendentesService service;

    // --- CRIAR TAREFA (POST) ---
    // Agora exige o ID na URL: http://localhost:8080/pendentes?idUser=1
    @PostMapping
    public ResponseEntity<Pendentes> criar(
            @RequestBody Pendentes tarefa, 
            @RequestParam Long idUser) { // <--- MUDANÇA AQUI: Recebe o ID do dono
        
        // Passa a tarefa E o ID do usuário para o service
        Pendentes novaTarefa = service.salvar(tarefa, idUser);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(novaTarefa);
    }

    // --- LISTAR TAREFAS (GET) ---
    // Agora exige o ID na URL: http://localhost:8080/pendentes?idUser=1
    @GetMapping
    public ResponseEntity<List<Pendentes>> listar(@RequestParam Long idUser) { // <--- MUDANÇA AQUI
        
        // Chama o método que filtra por usuário
        return ResponseEntity.ok(service.listarPorUsuario(idUser));
    }
    
    // --- DELETAR (DELETE) ---
    // Esse não muda, pois deletamos pelo ID da tarefa
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}