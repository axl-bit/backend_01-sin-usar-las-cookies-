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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "motorizado")
public class Motorizado implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "motorizado_id")
	private Integer motorizado_id;
	
	@Column(name = "codigoSoat", nullable = false)
	private String codigoSoat;

	@Column(name = "NroMatricula", nullable = false)
	private String NroMatricula;
	
	@Column(name = "UltimaRT", nullable = false)
	private Date UltimaRT;
	
	@OneToOne
	@JoinColumn(name = "colaborador_id", nullable = false, unique = true,
	foreignKey = @ForeignKey(foreignKeyDefinition = "foreign key(colaborador_id) references Colaborador(colaborador_id)"))
	@JsonBackReference
	private Colaborador colaborador;
	
	@ManyToOne
	@JoinColumn(name = "disponibilidad_id", nullable = false,
	foreignKey = @ForeignKey(foreignKeyDefinition = "foreign key(disponibilidad_id) references Disponibilidad(disponibilidad_id)"))
	private Disponibilidad disponibilidad;

	
	@ManyToOne
	@JoinColumn(name = "zona_id", nullable = false,
	foreignKey = @ForeignKey(foreignKeyDefinition = "foreign key(zona_id) references zona(zona_id)"))
	private Zona zona;
	
	@OneToMany(mappedBy = "motorizadorecojo")
	@JsonBackReference(value = "motorizado_OR")
	private Collection<OrdenRecojo> ORList = new ArrayList<>();
	
	@OneToMany(mappedBy = "motorizadoentrega")
	@JsonBackReference(value = "motorizado_OE")
	private Collection<OrdenEntrega> OEList = new ArrayList<>();
	

	public Motorizado() {
		super();
	}
	
	public Motorizado(String codigoSoat, String nroMatricula, Date ultimaRT, Colaborador colaborador,
			Disponibilidad disponibilidad, Zona zona) {
		super();
		this.codigoSoat = codigoSoat;
		this.NroMatricula = nroMatricula;
		this.UltimaRT = ultimaRT;
		this.colaborador = colaborador;
		this.disponibilidad = disponibilidad;
		this.zona = zona;
	}
	
	public Integer getMotorizado_id() {
		return motorizado_id;
	}
	public void setMotorizado_id(Integer motorizado_id) {
		this.motorizado_id = motorizado_id;
	}
	public String getCodigoSoat() {
		return codigoSoat;
	}
	public void setCodigoSoat(String codigoSoat) {
		this.codigoSoat = codigoSoat;
	}
	public String getNroMatricula() {
		return NroMatricula;
	}
	public void setNroMatricula(String nroMatricula) {
		NroMatricula = nroMatricula;
	}
	public Date getUltimaRT() {
		return UltimaRT;
	}
	public void setUltimaRT(Date ultimaRT) {
		UltimaRT = ultimaRT;
	}
	public Colaborador getColaborador() {
		return colaborador;
	}
	public void setColaborador(Colaborador colaborador) {
		this.colaborador = colaborador;
	}
	public Disponibilidad getDisponibilidad() {
		return disponibilidad;
	}
	public void setDisponibilidad(Disponibilidad disponibilidad) {
		this.disponibilidad = disponibilidad;
	}
	public Zona getZona() {
		return zona;
	}
	public void setZona(Zona zona) {
		this.zona = zona;
	}

}
