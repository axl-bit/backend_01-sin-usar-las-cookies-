package pe.company.pmmcourier.service;

import java.util.Collection;

import pe.company.pmmcourier.model.Estado;

public interface EstadoService {
	
	public abstract void insert(Estado est);
	public abstract void update(Estado est);
	public abstract void delete(Integer estID);
	public abstract Estado findById(Integer estID);
	public abstract Collection<Estado> findAll();

}
