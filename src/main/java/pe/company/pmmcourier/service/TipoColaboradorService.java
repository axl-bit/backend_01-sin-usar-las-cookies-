package pe.company.pmmcourier.service;

import java.util.Collection;

import pe.company.pmmcourier.model.TipoColaborador;

public interface TipoColaboradorService {
	
	public abstract void insert(TipoColaborador Tipo_Colaborador);
	public abstract void update(TipoColaborador Tipo_Colaborador);
	public abstract void delete(Integer TipoCID);
	public abstract TipoColaborador findById(Integer TipoCID);
	public abstract Collection<TipoColaborador> findAll();

}
