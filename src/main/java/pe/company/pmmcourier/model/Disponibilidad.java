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
@Table(name = "disponibilidad")
public class Disponibilidad implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "disponibilidad_id")
	private Integer disponibilidad_id;
	
	@Column(name = "disponibilidad", nullable = false)
	private String disponibilidad;
	
	@OneToMany(mappedBy = "disponibilidad")
	@JsonBackReference(value = "motorizado_disp")
	private Collection<Motorizado> MotorizadoList = new ArrayList<>();
	
	public Disponibilidad() {
		// TODO Auto-generated constructor stub
	}

	public Disponibilidad( String disponibilidad) {
		super();
		this.disponibilidad = disponibilidad;
	}

	public Integer getDisponibilidad_id() {
		return disponibilidad_id;
	}

	public void setDisponibilidad_id(Integer disponibilidad_id) {
		this.disponibilidad_id = disponibilidad_id;
	}

	public String getDisponibilidad() {
		return disponibilidad;
	}

	public void setDisponibilidad(String disponibilidad) {
		this.disponibilidad = disponibilidad;
	}

}
