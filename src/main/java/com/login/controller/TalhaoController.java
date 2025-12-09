package com.login.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.login.entities.talhao;
import com.login.service.TalhaoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/talhao")
// CONFIGURAÇÃO CORS: Permite que o Next.js (porta 3000) converse com o Java
@CrossOrigin(origins = "*")
public class TalhaoController {

    @Autowired
    private final TalhaoService talhoesService;

    public TalhaoController(TalhaoService talhoesService) {
        this.talhoesService = talhoesService;
    }

    // --- 1. CRIAR TALHÃO (POST) ---
    @PostMapping
    public ResponseEntity<talhao> criarTalhao(
            @RequestBody @Valid talhao talhoes, 
            @RequestParam Long idUser) { 
        
        talhao novoTalhoes = talhoesService.salvarTalhoes(talhoes, idUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoTalhoes);
    }

    // --- 2. BUSCAR TODOS DO USUÁRIO (GET) ---
    @GetMapping
    public ResponseEntity<List<talhao>> buscarTalhoesDoUsuario(@RequestParam Long idUser) {
        List<talhao> talhoes = talhoesService.buscarTalhoesDoUsuario(idUser);
        return ResponseEntity.ok(talhoes);
    }

    // --- 3. BUSCAR POR ID DO TALHÃO (GET) ---
    @GetMapping("/{idTalhao}")
    public ResponseEntity<talhao> buscarTalhoesId(@PathVariable Long idTalhao) {
        talhao talhoes = talhoesService.buscarTalhoesPorId(idTalhao);
        if (talhoes != null) {
            return ResponseEntity.ok(talhoes);
        }
        return ResponseEntity.notFound().build();
    }
    
    // --- 4. ATUALIZAR TALHÃO (PUT) ---
    @PutMapping("/{id}")
    public ResponseEntity<talhao> atualizarTalhao(
            @PathVariable Long id, 
            @RequestBody @Valid talhao talhaoAtualizado) {
        
        // Ajustado para chamar 'atualizarTalhoes' do seu Service
        talhao talhao = talhoesService.atualizarTalhoes(id, talhaoAtualizado); 
        
        if (talhao != null) {
            return ResponseEntity.ok(talhao);
        }
        return ResponseEntity.notFound().build();
    }

    // --- 5. DELETAR TALHÃO (DELETE) ---
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarTalhao(@PathVariable Long id) {
        
        System.out.println("--- DELETE: Recebido pedido para ID: " + id + " ---");
        
        // Ajustado para chamar 'apagarTalhoes' do seu Service
        Boolean apagou = talhoesService.apagarTalhoes(id); 
        
        if (apagou) {
            return ResponseEntity.noContent().build(); // Retorna 204 (Sucesso sem conteúdo)
        } else {
            return ResponseEntity.notFound().build(); // Retorna 404 se não achou pra apagar
        }
    }
}