package br.com.unitri.jheimesilveira.locacaoveiculo.domain;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;
import java.util.Date;

import br.com.unitri.jheimesilveira.locacaoveiculo.utils.Utils;


/**
 * The persistent class for the Locacao database table.
 * 
 */
@DatabaseTable
public class DataItem implements Serializable {
	private static final long serialVersionUID = 1L;

	@DatabaseField(generatedId = true)
	private Integer id;

	@DatabaseField(foreign = true, columnName = "idItemPai")
	private DataItem pai;

	@DatabaseField(dataType = DataType.DATE_STRING, format = "yyyy-MM-dd HH:mm:ss")
	private Date dtaInicio = new Date();

	@DatabaseField(dataType = DataType.DATE_STRING, format = "yyyy-MM-dd HH:mm:ss")
	private Date dtaFim;

	@DatabaseField(dataType = DataType.DATE_STRING, format = "yyyy-MM-dd HH:mm:ss")
	private Date dtaIntervalo;

	@DatabaseField
	private Integer intervalo = 0;

	public Integer getIntervalo() {
		return intervalo;
	}

	public void setIntervalo(Integer intervalo) {
		this.intervalo = intervalo;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public DataItem getPai() {
		return pai;
	}

	public void setPai(DataItem pai) {
		this.pai = pai;
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

	public Date getDtaIntervalo() {
		return dtaIntervalo;
	}

	public void setDtaIntervalo(Date dtaIntervalo) {
		this.dtaIntervalo = dtaIntervalo;
	}

	@Override
	public String toString() {
		if (pai == null)
		return
				"Data inicio: "+ Utils.formatDate("dd/MM/yyyy HH:mm", dtaInicio) +
				"\nData final: "+ Utils.formatDate("dd/MM/yyyy HH:mm", dtaFim);

		return "Intervalo: "+ Utils.formatDate("dd/MM/yyyy HH:mm", dtaIntervalo);
	}
}