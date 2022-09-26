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
@Table(name = "Provincia")
public class Provincia implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "provincia_id")
	private Integer provinciaID;
	
	@Column(name = "provincia", nullable = false)
	private String Provincia;
	
	@OneToMany(mappedBy = "Provincia")
	@JsonBackReference(value = "provincia_dist")
	private Collection<Distrito> DistritoPList = new ArrayList<>();
	
		
	public Provincia() {
		super();
	}


	public Provincia(String provincia) {
		this.Provincia = provincia;
	}


	public Integer getProvinciaID() {
		return provinciaID;
	}


	public void setProvinciaID(Integer provinciaID) {
		this.provinciaID = provinciaID;
	}


	public String getProvincia() {
		return Provincia;
	}


	public void setProvincia(String provincia) {
		this.Provincia = provincia;
	}

}