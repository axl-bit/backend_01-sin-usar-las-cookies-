package pe.company.pmmcourier.service;

import java.util.Collection;

import pe.company.pmmcourier.model.Zona;

public interface ZonaService {
	
	public abstract void insert(Zona zona);
	public abstract void update(Zona zona);
	public abstract void delete(Integer zonaID);
	public abstract Zona findById(Integer zonabID);
	public abstract Collection<Zona> findAll();

}
