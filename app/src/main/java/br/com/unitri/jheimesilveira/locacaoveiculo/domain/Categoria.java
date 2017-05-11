package br.com.unitri.jheimesilveira.locacaoveiculo.domain;

import java.io.Serializable;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.unitri.jheimesilveira.locacaoveiculo.utils.Utils;

/**
 * The persistent class for the Categoria database table.
 * 
 */
@DatabaseTable
public class Categoria implements Serializable {
	private static final long serialVersionUID = 1L;

	@DatabaseField(generatedId = true)
	private Integer id;

	@DatabaseField
	private String descricao;

	@DatabaseField
	private String preco;

	public Categoria() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getPreco() {
		return preco;
	}

	public void setPreco(String preco) {
		this.preco = preco;
	}

	@Override
	public String toString() {
		return descricao + "\nPreço: R$ " + preco;
	}
}