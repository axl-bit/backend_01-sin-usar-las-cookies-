package pe.company.pmmcourier.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "detalleservicio")
public class ServicioDetalle implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "detalleservicio_id")
	private Integer detalleservicio_id;
	
	@ManyToOne
	@JoinColumn(name = "recojo_id",nullable = false,
	foreignKey = @ForeignKey(foreignKeyDefinition = "foreign key(recojo_id) references ordenrecojo(recojo_id)"))
	private OrdenRecojo Ordenrecojo;
	
	@ManyToOne
	@JoinColumn(name = "entrega_id",nullable = false,
	foreignKey = @ForeignKey(foreignKeyDefinition = "foreign key(entrega_id) references ordenentrega(entrega_id)"))
	private OrdenEntrega Ordenentrega;
	
	@ManyToOne
	@JoinColumn(name = "estado_id",nullable = true,
	foreignKey = @ForeignKey(foreignKeyDefinition = "foreign key(estado_id) references estado(estado_id)"))
	private Estado estado;

	public ServicioDetalle() {
		// TODO Auto-generated constructor stub
	}

	public ServicioDetalle(OrdenRecojo ordenrecojo, OrdenEntrega ordenentrega, Estado estado) {
		super();
		this.Ordenrecojo = ordenrecojo;
		this.Ordenentrega = ordenentrega;
		this.estado = estado;
	}

	public Integer getDetalleservicio_id() {
		return detalleservicio_id;
	}

	public void setDetalleservicio_id(Integer detalleservicio_id) {
		this.detalleservicio_id = detalleservicio_id;
	}

	public OrdenRecojo getOrdenrecojo() {
		return Ordenrecojo;
	}

	public void setOrdenrecojo(OrdenRecojo ordenrecojo) {
		Ordenrecojo = ordenrecojo;
	}

	public OrdenEntrega getOrdenentrega() {
		return Ordenentrega;
	}

	public void setOrdenentrega(OrdenEntrega ordenentrega) {
		Ordenentrega = ordenentrega;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	
}
