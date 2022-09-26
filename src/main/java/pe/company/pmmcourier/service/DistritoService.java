package pe.company.pmmcourier.service;

import java.util.Collection;

import pe.company.pmmcourier.model.Distrito;

public interface DistritoService {
	
	public abstract void insert(Distrito dist);
	public abstract void update(Distrito dist);
	public abstract void delete(Integer distID);
	public abstract Distrito findById(Integer distID);
	public abstract Collection<Distrito> findAll();

}
