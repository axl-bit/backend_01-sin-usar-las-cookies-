package pe.company.pmmcourier.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

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
@Table(name = "paquete")
public class Paquete implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "paquete_id")
	private Integer paquete_id;
	
	@Column(name = "descripcionpqt", nullable = false)
	private String Descripcionpqt;
	
	@Column(name = "cantidadpqt", nullable = false)
	private Integer Cantidadpqt;
	
	@ManyToOne
	@JoinColumn(name = "tipopqt_id", nullable = false,
	foreignKey = @ForeignKey(foreignKeyDefinition = "foreign key(tipopqt_id) references TipoPQT(tipopqt_id)"))
	private TipoPQT tipopqt;

	
	@OneToMany(mappedBy = "paquete")
	@JsonBackReference(value = "pqt_OR")
	private Collection<OrdenRecojo> ORPQTList = new ArrayList<>();
	
	@OneToMany(mappedBy = "paquete")
	@JsonBackReference(value = "pqt_OE")
	private Collection<OrdenEntrega> OEPQTList = new ArrayList<>();
	
	
	public Paquete() {
		// TODO Auto-generated constructor stub
	}

	public Paquete( String descripcionpqt, Integer cantidadpqt, TipoPQT tipopqt) {
		super();
		this.Descripcionpqt = descripcionpqt;
		this.Cantidadpqt = cantidadpqt;
		this.tipopqt = tipopqt;
	}

	public Integer getPaquete_id() {
		return paquete_id;
	}

	public void setPaquete_id(Integer paquete_id) {
		this.paquete_id = paquete_id;
	}

	public String getDescripcionpqt() {
		return Descripcionpqt;
	}

	public void setDescripcionpqt(String descripcionpqt) {
		Descripcionpqt = descripcionpqt;
	}

	public Integer getCantidadpqt() {
		return Cantidadpqt;
	}

	public void setCantidadpqt(Integer cantidadpqt) {
		Cantidadpqt = cantidadpqt;
	}

	public TipoPQT getTipopqt() {
		return tipopqt;
	}

	public void setTipopqt(TipoPQT tipopqt) {
		this.tipopqt = tipopqt;
	}
	
}
