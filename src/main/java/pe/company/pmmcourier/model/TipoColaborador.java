package pe.company.pmmcourier.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "TipoColaborador")
public class TipoColaborador implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "tipoC_id")
	private Integer tipoCID;
	
	@Column(name = "tipo_colaborador", nullable = false)
	private String tipo_colaborador;
	
	@OneToMany(mappedBy = "tipo_colaborador")
	@JsonBackReference(value = "tipoC_colaborador")
	private Collection<Colaborador> ColaboradorList = new ArrayList<>();

		
	public TipoColaborador() {
		super();
	}


	public TipoColaborador(Integer tipoCID, String tipo_colaborador, Collection<Colaborador> colaboradorList) {
		super();
		this.tipoCID = tipoCID;
		this.tipo_colaborador = tipo_colaborador;
		ColaboradorList = colaboradorList;
	}


	public Integer getTipoCID() {
		return tipoCID;
	}


	public void setTipoCID(Integer tipoCID) {
		this.tipoCID = tipoCID;
	}


	public String getTipo_colaborador() {
		return tipo_colaborador;
	}


	public void setTipo_colaborador(String tipo_colaborador) {
		this.tipo_colaborador = tipo_colaborador;
	}

}
