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
@Table(name = "TipoPQT")
public class TipoPQT implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "tipopqt_id")
	private Integer TipoPQTID;
	
	@Column(name = "tipopqt", nullable = false)
	private String TipoPQT;
	
	@OneToMany(mappedBy = "tipopqt")
	@JsonBackReference(value = "Paquete_tipo")
	private Collection<Paquete> PaqueteList = new ArrayList<>();
	

	public TipoPQT() {
		super();
	}



	public TipoPQT(String tipoPQT) {
		super();
		TipoPQT = tipoPQT;
	}



	public Integer getTipoPQTID() {
		return TipoPQTID;
	}



	public void setTipoPQTID(Integer tipoPQTID) {
		TipoPQTID = tipoPQTID;
	}



	public String getTipoPQT() {
		return TipoPQT;
	}



	public void setTipoPQT(String tipoPQT) {
		TipoPQT = tipoPQT;
	}
}
