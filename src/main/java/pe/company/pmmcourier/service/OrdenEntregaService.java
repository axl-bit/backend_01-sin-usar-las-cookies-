package pe.company.pmmcourier.service;

import java.util.Collection;

import pe.company.pmmcourier.model.OrdenEntrega;

public interface OrdenEntregaService {
	
	public abstract void insert(OrdenEntrega OE);
	public abstract void update(OrdenEntrega OE);
	public abstract void delete(Integer oeID);
	public abstract OrdenEntrega findById(Integer oeID);
	public abstract Collection<OrdenEntrega> findAll();

}
