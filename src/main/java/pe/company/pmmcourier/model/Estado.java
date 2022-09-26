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
@Table(name = "estado")
public class Estado implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "estado_id", unique = true)
	private Integer EstadoID;
	
	@Column(name = "estado", nullable = false)
	private String Estado;
	
	@OneToMany(mappedBy = "estado")
	@JsonBackReference(value = "estado_ds")
	private Collection<ServicioDetalle> SDESList = new ArrayList<>();
	

	public Estado() {
		super();
	}

	public Estado(String estado) {
		super();
		Estado = estado;
	}

	public Integer getEstadoID() {
		return EstadoID;
	}

	public void setEstadoID(Integer estadoID) {
		EstadoID = estadoID;
	}

	public String getEstado() {
		return Estado;
	}

	public void setEstado(String estado) {
		Estado = estado;
	}
	
}
