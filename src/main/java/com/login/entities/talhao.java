package com.login.entities;

import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "Talhoes")
public class talhao {

    public talhao() {
        super();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTalhao;

    @Column(nullable = false)
    @JsonProperty("nomeTalhao")
    private String nomeTalhao; 

    @Column(nullable = false)
    @JsonProperty("cultura")
    private String cultura;

    @Column(nullable = false)
    @JsonProperty("variedade")
    private String variedade;

    @Column(nullable = false)
    @JsonProperty("estagioIni")
    private String estagioIni; 

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(nullable = false)
    @JsonProperty("dataPlantio")
    private LocalDate dataPlantio;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(nullable = false)
    @JsonProperty("dataColheita")
    private LocalDate dataColheita;

    @Column(nullable = true)
    @JsonProperty("notasAdicionais")
    private String notasAdicionais;
    
    @ManyToOne 
    @JoinColumn(name = "id_user") 
    @JsonIgnore 
    private login usuario; 

    // =================================================================
    // AQUI ESTÃO OS GETTERS E SETTERS QUE FALTAVAM
    // =================================================================

    public Long getIdTalhao() {
        return idTalhao;
    }

    public void setIdTalhao(Long idTalhao) {
        this.idTalhao = idTalhao;
    }

    public String getNomeTalhao() {
        return nomeTalhao;
    }

    public void setNomeTalhao(String nomeTalhao) {
        this.nomeTalhao = nomeTalhao;
    }

    public String getCultura() {
        return cultura;
    }

    public void setCultura(String cultura) {
        this.cultura = cultura;
    }

    public String getVariedade() {
        return variedade;
    }

    public void setVariedade(String variedade) {
        this.variedade = variedade;
    }

    public String getEstagioIni() {
        return estagioIni;
    }

    public void setEstagioIni(String estagioIni) {
        this.estagioIni = estagioIni;
    }

    public LocalDate getDataPlantio() {
        return dataPlantio;
    }

    public void setDataPlantio(LocalDate dataPlantio) {
        this.dataPlantio = dataPlantio;
    }

    public LocalDate getDataColheita() {
        return dataColheita;
    }

    public void setDataColheita(LocalDate dataColheita) {
        this.dataColheita = dataColheita;
    }

    public String getNotasAdicionais() {
        return notasAdicionais;
    }

    public void setNotasAdicionais(String notasAdicionais) {
        this.notasAdicionais = notasAdicionais;
    }

    public login getUsuario() {
        return usuario;
    }

    public void setUsuario(login usuario) {
        this.usuario = usuario;
    }
}