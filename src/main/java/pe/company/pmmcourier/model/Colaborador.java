package pe.company.pmmcourier.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;



@Entity
@Table(name = "Colaborador")
public class Colaborador implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "colaborador_id",unique = true)
	private Integer colaboradorID;
	
	@Column(name = "dni", length = 8, nullable = false, unique = true)
	private String dnicolaborador;
	
	@Column(name = "nombre_Colaborador",length = 80, nullable = false)
	private String nombreC;
	
	@Column(name = "usuario", length = 30, nullable = false, unique = true)
	private String usuario;
	
	@Column(name = "contrasena",nullable = false, unique = true)
	private String contrasena;
	
	@ManyToOne
	@JoinColumn(name = "tipoC_id", nullable = false,
	foreignKey = @ForeignKey(foreignKeyDefinition = "foreign key(tipoC_id) references Tipo_Colaborador(tipoC_id)"))
	private TipoColaborador tipo_colaborador;

	@OneToOne(mappedBy = "colaborador", cascade = CascadeType.ALL)
	private Motorizado motorizado;

	public Colaborador() {
		super();
	}



	public Colaborador(String dnicolaborador, String nombreC, String usuario, String contrasena,
			TipoColaborador tipo_colaborador) {
		super();
		this.dnicolaborador = dnicolaborador;
		this.nombreC = nombreC;
		this.usuario = usuario;
		this.contrasena = contrasena;
		this.tipo_colaborador = tipo_colaborador;
	}



	public Integer getColaboradorID() {
		return colaboradorID;
	}



	public void setColaboradorID(Integer colaboradorID) {
		this.colaboradorID = colaboradorID;
	}



	public String getDnicolaborador() {
		return dnicolaborador;
	}



	public void setDnicolaborador(String dnicolaborador) {
		this.dnicolaborador = dnicolaborador;
	}



	public String getNombreC() {
		return nombreC;
	}



	public void setNombreC(String nombreC) {
		this.nombreC = nombreC;
	}



	public String getUsuario() {
		return usuario;
	}



	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}



	public String getContrasena() {
		return contrasena;
	}



	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}



	public TipoColaborador getTipo_colaborador() {
		return tipo_colaborador;
	}



	public void setTipo_colaborador(TipoColaborador tipo_colaborador) {
		this.tipo_colaborador = tipo_colaborador;
	}

	
}
