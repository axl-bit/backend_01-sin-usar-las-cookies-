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
@Table(name = "zona")
public class Zona implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "zona_id")
	private Integer Zona_id;
	
	@Column(name = "zona", nullable = false)
	private String Zona;

	@OneToMany(mappedBy = "zona")
	@JsonBackReference(value = "zona_motorizado")
	private Collection<Motorizado> MotorizadoList = new ArrayList<>();
	
	public Zona() {
		// TODO Auto-generated constructor stub
	}

	public Zona(String zona) {
		super();
		Zona = zona;
	}

	public Integer getZona_id() {
		return Zona_id;
	}

	public void setZona_id(Integer zona_id) {
		Zona_id = zona_id;
	}

	public String getZona() {
		return Zona;
	}

	public void setZona(String zona) {
		Zona = zona;
	}

}
