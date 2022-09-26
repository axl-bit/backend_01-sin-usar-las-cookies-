package pe.company.pmmcourier.service;

import java.util.Collection;

import pe.company.pmmcourier.model.TipoPQT;

public interface TipoPQTService {
	
	public abstract void insert(TipoPQT tipoPQT);
	public abstract void update(TipoPQT tipoPQT);
	public abstract void delete(Integer tipoPQTID);
	public abstract TipoPQT findById(Integer tipoPQTID);
	public abstract Collection<TipoPQT> findAll();

}
