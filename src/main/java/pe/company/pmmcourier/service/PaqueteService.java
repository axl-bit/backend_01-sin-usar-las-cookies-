package pe.company.pmmcourier.service;

import java.util.Collection;

import pe.company.pmmcourier.model.Paquete;

public interface PaqueteService {
	
	public abstract void insert(Paquete pqt);
	public abstract void update(Paquete pqt);
	public abstract void delete(Integer pqtID);
	public abstract Paquete findById(Integer pqtID);
	public abstract Collection<Paquete> findAll();

}
