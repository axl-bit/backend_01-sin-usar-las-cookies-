package pe.company.pmmcourier.service;

import java.util.Collection;

import pe.company.pmmcourier.model.Provincia;

public interface ProvinciaService {
	
	public abstract void insert(Provincia prov);
	public abstract void update(Provincia prov);
	public abstract void delete(Integer provID);
	public abstract Provincia findById(Integer provID);
	public abstract Collection<Provincia> findAll();

}
