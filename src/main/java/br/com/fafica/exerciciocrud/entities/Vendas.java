package br.com.fafica.exerciciocrud.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import br.com.fafica.exerciciocrud.enums.PerfilEnum;

@Entity
@Table(name = "funcionario")
public class Vendas implements Serializable {

	private static final long serialVersionUID = -5754246207015712518L;

	private Long id;
	private String descricao;
	private Date dataPagamento;
	private Date dataVencimento;
	private double Valor;
	private Pessoa vendas;
	private List<Lancamento> lancamentos;
	private PerfilEnum perfil;

	public Vendas() {
	}

	@Column(name = "descricao, nullable = true")
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "data_pagamento", nullable = false)
	public Date getdataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	@Column(name = "data_vencimento", nullable = false)
	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimentoo(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	public Pessoa getVendas() {
		return vendas;
	}

	public void setVendas(Pessoa vendas) {
		this.vendas = vendas;
	}

	@OneToMany(mappedBy = "vendas", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	public List<Lancamento> getLancamentos() {
		return lancamentos;
	}

	public void setLancamentos(List<Lancamento> lancamentos) {
		this.lancamentos = lancamentos;
	}

	@Column(name = "Valor", nullable = true)
	public double getValor() {
		return Valor;
	}

	public void setValor(double valor) {
		Valor = valor;
	}

	// ========================== ENUM ==================================

	@Enumerated(EnumType.STRING)
	@Column(name = "perfil", nullable = false)
	public PerfilEnum getPerfil() {
		return perfil;
	}

	public void setPerfil(PerfilEnum perfil) {
		this.perfil = perfil;
	}

	@PreUpdate
	public void preUpdate() {
		dataVencimento = new Date();
	}

	@PrePersist
	public void prePersist() {
		final Date atual = new Date();
		dataPagamento = atual;
		dataVencimento = atual;
	}

}
