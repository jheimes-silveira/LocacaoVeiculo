package br.com.unitri.jheimesilveira.locacaoveiculo.domain;

import java.io.Serializable;
import java.util.Date;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.unitri.jheimesilveira.locacaoveiculo.utils.Utils;


/**
 * The persistent class for the Locacao database table.
 * 
 */
@DatabaseTable
public class Locacao implements Serializable {
	private static final long serialVersionUID = 1L;

	@DatabaseField(generatedId = true)
	private Integer id;

	@DatabaseField(dataType = DataType.DATE_STRING, format = "yyyy-MM-dd HH:mm:ss")
	private Date dtaInicio = new Date();

	@DatabaseField(dataType = DataType.DATE_STRING, format = "yyyy-MM-dd HH:mm:ss")
	private Date dtaFim;

	@DatabaseField(foreign=true, canBeNull = false)
	private Cliente cliente;

	@DatabaseField(foreign=true, canBeNull = false)
	private Funcionario funcionarioCad;

	@DatabaseField(foreign=true, canBeNull = false)
	private Funcionario funcionarioRec;

	@DatabaseField(foreign=true, canBeNull = false)
	private Veiculo veiculo;

	public Locacao() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDtaInicio() {
		return dtaInicio;
	}

	public void setDtaInicio(Date dtaInicio) {
		this.dtaInicio = dtaInicio;
	}

	public Date getDtaFim() {
		return dtaFim;
	}

	public void setDtaFim(Date dtaFim) {
		this.dtaFim = dtaFim;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Funcionario getFuncionarioCad() {
		return funcionarioCad;
	}

	public void setFuncionarioCad(Funcionario funcionarioCad) {
		this.funcionarioCad = funcionarioCad;
	}

	public Funcionario getFuncionarioRec() {
		return funcionarioRec;
	}

	public void setFuncionarioRec(Funcionario funcionarioRec) {
		this.funcionarioRec = funcionarioRec;
	}

	public Veiculo getVeiculo() {
		return veiculo;
	}

	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}

	@Override
	public String toString() {
		return
				"Periodo: " + Utils.formatDate("dd/MM/yyyy HH:mm", dtaInicio) +" - " +
				Utils.formatDate("dd/MM/yyyy HH:mm", dtaFim)+
				"\nVeiculo: " + veiculo.getModelo() + " - " + veiculo.getAno();
	}
}