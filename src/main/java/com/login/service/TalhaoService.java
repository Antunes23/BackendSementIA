package com.login.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.login.entities.login; // Importando a entidade de Login
import com.login.entities.talhao;
import com.login.repository.LoginRepository; // Importando o repositório de Login
import com.login.repository.TalhaoRepository;

@Service
public class TalhaoService {

    @Autowired
    private final TalhaoRepository talhoesRepository;
    
    @Autowired
    private LoginRepository loginRepository; // ADICIONADO: Precisamos disso para achar o dono!
    
    public TalhaoService(TalhaoRepository talhoesRepository) {
        this.talhoesRepository = talhoesRepository;
    }
    
    // --- 1. ALTERADO: BUSCAR APENAS DO USUÁRIO ---
    // Recebe o ID do usuário, busca o objeto Usuário e filtra os talhões dele
    public List<talhao> buscarTalhoesDoUsuario(Long idUser) {
        // Busca o usuário no banco pelo ID
        Optional<login> usuario = loginRepository.findById(idUser);
        
        if(usuario.isPresent()) {
            // Se achou o usuário, chama aquele método novo do Repository
            return talhoesRepository.findByUsuario(usuario.get());
        } else {
            // Se o usuário não existe, retorna lista vazia ou lança erro
            return List.of(); 
        }
    }
    
    // Mantemos esse para buscar um específico (detalhes)
    public talhao buscarTalhoesPorId(Long idTalhoes) {
        Optional<talhao> talhoes = talhoesRepository.findById(idTalhoes);
        return talhoes.orElse(null);
    }
    
    // --- 2. ALTERADO: SALVAR COM DONO ---
    // Agora recebe o Talhão E o ID de quem está criando
    public talhao salvarTalhoes(talhao Talhoes, Long idUser) {
        // Busca o usuário dono
        Optional<login> usuario = loginRepository.findById(idUser);
        
        if(usuario.isPresent()) {
            // Vincula o usuário ao talhão antes de salvar
            Talhoes.setUsuario(usuario.get());
            return talhoesRepository.save(Talhoes);
        } else {
            throw new RuntimeException("Erro: Usuário não encontrado para criar o talhão.");
        }
    }
    
    // --- ATUALIZAR ---
    public talhao atualizarTalhoes(Long idTalhoes, talhao atTalhoes) {
        Optional<talhao> exeTalhoes = talhoesRepository.findById(idTalhoes);
        if (exeTalhoes.isPresent()) {
            talhao talhaoExistente = exeTalhoes.get();
            
            // Atualiza os dados
            talhaoExistente.setNomeTalhao(atTalhoes.getNomeTalhao());
            talhaoExistente.setCultura(atTalhoes.getCultura());
            talhaoExistente.setVariedade(atTalhoes.getVariedade());
            talhaoExistente.setEstagioIni(atTalhoes.getEstagioIni());
            talhaoExistente.setDataPlantio(atTalhoes.getDataPlantio());
            talhaoExistente.setDataColheita(atTalhoes.getDataColheita());
            talhaoExistente.setNotasAdicionais(atTalhoes.getNotasAdicionais());
            
            // O usuário (dono) não muda na edição, então o Hibernate mantém o que já estava
            
            return talhoesRepository.save(talhaoExistente);
        } else {
            return null;
        }
    }
    
    public Boolean apagarTalhoes(Long idTalhoes) {
        Optional<talhao> exeTalhoes = talhoesRepository.findById(idTalhoes);
        if (exeTalhoes.isPresent()) {
            talhoesRepository.deleteById(idTalhoes);
            return true;
        } else {
            return false;
        }
    }
}