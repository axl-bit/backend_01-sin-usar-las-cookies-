package pe.company.pmmcourier.service;

import java.util.Collection;

import pe.company.pmmcourier.model.Colaborador;

public interface ColaboradorService {
	
	public abstract void insert(Colaborador colab);
	public abstract void update(Colaborador colab);
	public abstract void delete(Integer colabID);
	public abstract Colaborador findById(Integer colabID);
	public abstract Collection<Colaborador> findAll();

}
