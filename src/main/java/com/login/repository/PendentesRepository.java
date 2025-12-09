package com.login.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.login.entities.Pendentes;
import com.login.entities.login; // <--- Importante: Importar a entidade de Login

@Repository
public interface PendentesRepository extends JpaRepository<Pendentes, Long> {
    
    // --- O SEGREDO ESTÁ AQUI ---
    // O Spring cria o SQL: "SELECT * FROM tarefas WHERE id_user = ?"
    List<Pendentes> findByUsuario(login usuario);

}