package com.login.entities;

import jakarta.persistence.*;
import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonIgnore; // <--- IMPORTANTE

@Entity
@Table(name = "tarefas") 
public class Pendentes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome; 

    private String talhaoAssociado;

    private LocalDate prazo;

    private String descricao;

    private String status = "PENDENTE";

    // --- AQUI ESTÁ A MUDANÇA: O VÍNCULO COM O USUÁRIO ---
    @ManyToOne
    @JoinColumn(name = "id_user") // Cria a coluna 'id_user' na tabela tarefas
    @JsonIgnore // Impede que o JSON fique gigante ou trave
    private login usuario; // Referência à classe 'login'

    // Construtores
    public Pendentes() {}

    public Pendentes(String nome, String talhaoAssociado, LocalDate prazo, String descricao) {
        this.nome = nome;
        this.talhaoAssociado = talhaoAssociado;
        this.prazo = prazo;
        this.descricao = descricao;
    }

    // --- GETTERS E SETTERS ---
    
    // Novo Getter/Setter do Usuário
    public login getUsuario() {
        return usuario;
    }

    public void setUsuario(login usuario) {
        this.usuario = usuario;
    }

    // Seus Getters/Setters antigos
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getTalhaoAssociado() { return talhaoAssociado; }
    public void setTalhaoAssociado(String talhaoAssociado) { this.talhaoAssociado = talhaoAssociado; }

    public LocalDate getPrazo() { return prazo; }
    public void setPrazo(LocalDate prazo) { this.prazo = prazo; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}