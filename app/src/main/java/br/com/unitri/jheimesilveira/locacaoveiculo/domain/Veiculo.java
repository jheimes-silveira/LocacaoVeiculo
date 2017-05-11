package br.com.unitri.jheimesilveira.locacaoveiculo.domain;

import java.io.Serializable;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the Veiculo database table.
 * 
 */
@DatabaseTable
public class Veiculo implements Serializable {
	private static final long serialVersionUID = 1L;

	@DatabaseField(generatedId = true)
	private Integer id;

	@DatabaseField
	private int ano;

	@DatabaseField
	private String chassi;

	@DatabaseField
	private String marca;

	@DatabaseField
	private String modelo;

	@DatabaseField
	private String observacao;

	@DatabaseField
	private BigDecimal valor;

	@DatabaseField(foreign = true)
	private Categoria categoria;

	public Veiculo() {
	}

	public String getChassi() {
		return chassi;
	}

	public void setChassi(String chassi) {
		this.chassi = chassi;
	}

	public int getAno() {
		return this.ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	public String getMarca() {
		return this.marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return this.modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getObservacao() {
		return this.observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public BigDecimal getValor() {
		return this.valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Categoria getCategoria() {
		return this.categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return modelo + " " + ano + "\nMarca: " + marca;
	}
}