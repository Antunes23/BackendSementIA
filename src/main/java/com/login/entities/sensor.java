package com.login.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
// Removi imports de Set, HashSet e OneToMany que não vamos usar mais

@Entity
@Table(name = "Sensor")
public class sensor {
	
	public sensor() {
		super();
	}
	
	public sensor(Long idSens, String descSens, String tipoSens, double valor) {
		super();
		this.idSens = idSens;
		this.descSens = descSens;
		this.tipoSens = tipoSens;
		this.valor = valor;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idSens;
	
	@Column(nullable = false)
	private String descSens;
	
	@Column(nullable = false)
	private String tipoSens;
	
	@Column(nullable = false)
	private double valor;
	
    // --- REMOVIDO A LISTA DE TALHÕES ---
    // private Set<talhao> Talhoes... (Tchau!)
    
    // --- GETTERS E SETTERS ---

	public double getValor() { return valor; }
	public void setValor(double valor) { this.valor = valor; }

	public Long getIdSens() { return idSens; }
	public void setIdSens(Long idSens) { this.idSens = idSens; }

	public String getDescSens() { return descSens; }
	public void setDescSens(String descSens) { this.descSens = descSens; }

	public String getTipoSens() { return tipoSens; }
	public void setTipoSens(String tipoSens) { this.tipoSens = tipoSens; }
}