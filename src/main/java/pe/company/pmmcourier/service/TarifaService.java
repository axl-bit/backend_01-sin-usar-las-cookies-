package pe.company.pmmcourier.service;

import java.util.Collection;

import pe.company.pmmcourier.model.Tarifa;

public interface TarifaService {

	public abstract void insert(Tarifa tarifa);
	public abstract void update(Tarifa tarifa);
	public abstract void delete(Integer tarifaID);
	public abstract Tarifa findById(Integer tarifaID);
	public abstract Collection<Tarifa> findAll();
	
}
