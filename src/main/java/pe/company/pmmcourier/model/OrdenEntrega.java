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
@Table(name = "ordenentrega")
public class OrdenEntrega implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "entrega_id")
	private Integer entrega_id;
	
	@ManyToOne
	@JoinColumn(name = "distrito_id",nullable = false,
	foreignKey = @ForeignKey(foreignKeyDefinition = "foreign key(distrito_id) references distrito(distrito_id)"))
	private Distrito DistritoE;
	
	@Column(name = "direccione", nullable = false)
	private String DireccionE;
	
	@Column(name = "fechaE", nullable = false)
	private Date FechaE;
	
	@Column(name = "dnirec", nullable = false)
	private String dniRec;
	
	@Column(name = "nomcomplReceptor", nullable = false)
	private String NomComplReceptor;
	
	@Column(name = "celularec", nullable = false)
	private String CelularRec;
	
	@ManyToOne
	@JoinColumn(name = "motorizado_id",nullable = true,
	foreignKey = @ForeignKey(foreignKeyDefinition = "foreign key(motorizado_id) references motorizado(motorizado_id)"))
	private Motorizado motorizadoentrega;
	
	@ManyToOne
	@JoinColumn(name = "paquete_id",nullable = false,
	foreignKey = @ForeignKey(foreignKeyDefinition = "foreign key(paquete_id) references paquete(paquete_id)"))
	private Paquete paquete;
	
	@OneToMany(mappedBy = "Ordenentrega")
	@JsonBackReference(value = "ordenentrega_ds")
	private Collection<ServicioDetalle> SDENList = new ArrayList<>();

	public OrdenEntrega() {
		// TODO Auto-generated constructor stub
	}

	public OrdenEntrega(Distrito distritoE, String direccionE, Date fechaE, String dniRec, String nomComplReceptor,
			String celularRec, Motorizado motorizadoentrega, Paquete paquete) {
		super();
		this.DistritoE = distritoE;
		this.DireccionE = direccionE;
		this.FechaE = fechaE;
		this.dniRec = dniRec;
		this.NomComplReceptor = nomComplReceptor;
		this.CelularRec = celularRec;
		this.motorizadoentrega = motorizadoentrega;
		this.paquete = paquete;
	}

	public Integer getEntrega_id() {
		return entrega_id;
	}

	public void setEntrega_id(Integer entrega_id) {
		this.entrega_id = entrega_id;
	}

	public Distrito getDistritoE() {
		return DistritoE;
	}

	public void setDistritoE(Distrito distritoE) {
		DistritoE = distritoE;
	}

	public String getDireccionE() {
		return DireccionE;
	}

	public void setDireccionE(String direccionE) {
		DireccionE = direccionE;
	}

	public Date getFechaE() {
		return FechaE;
	}

	public void setFechaE(Date fechaE) {
		FechaE = fechaE;
	}

	public String getDniRec() {
		return dniRec;
	}

	public void setDniRec(String dniRec) {
		this.dniRec = dniRec;
	}

	public String getNomComplReceptor() {
		return NomComplReceptor;
	}

	public void setNomComplReceptor(String nomComplReceptor) {
		NomComplReceptor = nomComplReceptor;
	}

	public String getCelularRec() {
		return CelularRec;
	}

	public void setCelularRec(String celularRec) {
		CelularRec = celularRec;
	}

	public Motorizado getMotorizadoentrega() {
		return motorizadoentrega;
	}

	public void setMotorizadoentrega(Motorizado motorizadoentrega) {
		this.motorizadoentrega = motorizadoentrega;
	}

	public Paquete getPaquete() {
		return paquete;
	}

	public void setPaquete(Paquete paquete) {
		this.paquete = paquete;
	}

}
