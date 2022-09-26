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
@Table(name = "Tarifa")
public class Tarifa implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "tarifa_id")
	private Integer TarifaID;
	
	@Column(name = "tarifa", nullable = false)
	private Double Tarifa;
	
	@OneToMany(mappedBy = "Tarifa")
	@JsonBackReference(value = "tarifa_dist")
	private Collection<Distrito> DistritoList = new ArrayList<>();

	public Tarifa() {
		super();
	}

	public Tarifa(Double tarifa) {
		super();
		Tarifa = tarifa;
	}

	public Integer getTarifaID() {
		return TarifaID;
	}

	public void setTarifaID(Integer tarifaID) {
		TarifaID = tarifaID;
	}

	public Double getTarifa() {
		return Tarifa;
	}

	public void setTarifa(Double tarifa) {
		Tarifa = tarifa;
	}
		
}
