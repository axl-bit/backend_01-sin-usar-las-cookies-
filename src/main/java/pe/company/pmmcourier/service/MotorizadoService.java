package pe.company.pmmcourier.service;

import java.util.Collection;

import pe.company.pmmcourier.model.Motorizado;

public interface MotorizadoService {

	public abstract void insert(Motorizado moto);
	public abstract void update(Motorizado moto);
	public abstract void delete(Integer motoID);
	public abstract Motorizado findById(Integer motoID);
	public abstract Collection<Motorizado> findAll();
	
}
