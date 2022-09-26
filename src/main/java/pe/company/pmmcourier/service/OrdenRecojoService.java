package pe.company.pmmcourier.service;

import java.util.Collection;

import pe.company.pmmcourier.model.OrdenRecojo;

public interface OrdenRecojoService {

	public abstract void insert(OrdenRecojo OR);
	public abstract void update(OrdenRecojo OR);
	public abstract void delete(Integer orID);
	public abstract OrdenRecojo findById(Integer orID);
	public abstract Collection<OrdenRecojo> findAll();
	
}
