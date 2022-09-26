package pe.company.pmmcourier.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "ordenrecojo")
public class OrdenRecojo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "recojo_id")
	private Integer recojo_id;
	
	@ManyToOne
	@JoinColumn(name = "distrito_id",nullable = false,
	foreignKey = @ForeignKey(foreignKeyDefinition = "foreign key(distrito_id) references distrito(distrito_id)"))
	private Distrito DistritoR;
	
	@Column(name = "direccionr", nullable = false)
	private String DireccionR;
	
	@Column(name = "fechaR", nullable = false)
	private Date FechaR;
	
	@Column(name = "dniem", nullable = false)
	private String dniEm;
	
	@Column(name = "nomcomplemisor", nullable = false)
	private String NomComplEmisor;
	
	@Column(name = "celularem", nullable = false)
	private String CelularEm;
	
	@ManyToOne
	@JoinColumn(name = "motorizado_id",nullable = true,
	foreignKey = @ForeignKey(foreignKeyDefinition = "foreign key(motorizado_id) references motorizado(motorizado_id)"))
	private Motorizado motorizadorecojo;
	
	@ManyToOne
	@JoinColumn(name = "paquete_id",nullable = false,
	foreignKey = @ForeignKey(foreignKeyDefinition = "foreign key(paquete_id) references paquete(paquete_id)"))
	private Paquete paquete;
	
	@OneToMany(mappedBy = "Ordenrecojo")
	@JsonBackReference(value = "ordenrecojo_ds")
	private Collection<ServicioDetalle> SDORList = new ArrayList<>();
	


	public OrdenRecojo() {
		// TODO Auto-generated constructor stub
	}


	public OrdenRecojo(Distrito distritoR, String direccionR, Date fechaR, String dniEm, String nomComplEmisor,
			String celularEm, Motorizado motorizadorecojo, Paquete paquete) {
		super();
		DistritoR = distritoR;
		DireccionR = direccionR;
		FechaR = fechaR;
		this.dniEm = dniEm;
		NomComplEmisor = nomComplEmisor;
		CelularEm = celularEm;
		this.motorizadorecojo = motorizadorecojo;
		this.paquete = paquete;
	}


	public Integer getRecojo_id() {
		return recojo_id;
	}


	public void setRecojo_id(Integer recojo_id) {
		this.recojo_id = recojo_id;
	}


	public Distrito getDistritoR() {
		return DistritoR;
	}


	public void setDistritoR(Distrito distritoR) {
		DistritoR = distritoR;
	}


	public String getDireccionR() {
		return DireccionR;
	}


	public void setDireccionR(String direccionR) {
		DireccionR = direccionR;
	}


	public Date getFechaR() {
		return FechaR;
	}


	public void setFechaR(Date fechaR) {
		FechaR = fechaR;
	}


	public String getDniEm() {
		return dniEm;
	}


	public void setDniEm(String dniEm) {
		this.dniEm = dniEm;
	}


	public String getNomComplEmisor() {
		return NomComplEmisor;
	}


	public void setNomComplEmisor(String nomComplEmisor) {
		NomComplEmisor = nomComplEmisor;
	}


	public String getCelularEm() {
		return CelularEm;
	}


	public void setCelularEm(String celularEm) {
		CelularEm = celularEm;
	}


	public Motorizado getMotorizadorecojo() {
		return motorizadorecojo;
	}


	public void setMotorizadorecojo(Motorizado motorizadorecojo) {
		this.motorizadorecojo = motorizadorecojo;
	}


	public Paquete getPaquete() {
		return paquete;
	}


	public void setPaquete(Paquete paquete) {
		this.paquete = paquete;
	}


}
