package pe.company.pmmcourier.service;

import java.util.Collection;

import pe.company.pmmcourier.model.Disponibilidad;

public interface DisponibilidadService {
	
	public abstract void insert(Disponibilidad dispo);
	public abstract void update(Disponibilidad dispo);
	public abstract void delete(Integer dispoID);
	public abstract Disponibilidad findById(Integer dispoID);
	public abstract Collection<Disponibilidad> findAll();


}
