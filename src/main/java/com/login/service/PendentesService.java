package com.login.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.login.entities.Pendentes;
import com.login.entities.login; // Importe a entidade Login
import com.login.repository.PendentesRepository;
import com.login.repository.LoginRepository; // Importe o repositório de Login
import java.util.List;
import java.util.Optional;

@Service
public class PendentesService {

    @Autowired
    private PendentesRepository repository;
    
    @Autowired
    private LoginRepository loginRepository; // 1. Precisamos disso para achar o dono

    // --- SALVAR TAREFA VINCULADA AO USUÁRIO ---
    public Pendentes salvar(Pendentes tarefa, Long idUser) {
        // Busca o usuário no banco
        Optional<login> usuario = loginRepository.findById(idUser);

        if (usuario.isPresent()) {
            // Vincula o usuário à tarefa antes de salvar
            tarefa.setUsuario(usuario.get());
            return repository.save(tarefa);
        } else {
            throw new RuntimeException("Erro: Usuário não encontrado para criar a tarefa.");
        }
    }

    // --- LISTAR TAREFAS APENAS DO USUÁRIO ---
    public List<Pendentes> listarPorUsuario(Long idUser) {
        // Busca o usuário
        Optional<login> usuario = loginRepository.findById(idUser);

        if (usuario.isPresent()) {
            // Retorna apenas as tarefas desse usuário
            return repository.findByUsuario(usuario.get());
        }
        
        // Se não achar o usuário, retorna lista vazia
        return List.of();
    }

    // Deletar continua igual (pelo ID da tarefa)
    public void deletar(Long id) {
        repository.deleteById(id);
    }
}