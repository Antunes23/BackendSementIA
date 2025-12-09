package com.login.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.login.entities.talhao;
import com.login.entities.login; // Importe a entidade de login

public interface TalhaoRepository extends JpaRepository<talhao, Long> {

    // --- O SEGREDO ESTÁ NESTA LINHA ---
    // O Spring cria automaticamente o SQL: "SELECT * FROM talhoes WHERE id_user = ?"
    List<talhao> findByUsuario(login usuario);

}