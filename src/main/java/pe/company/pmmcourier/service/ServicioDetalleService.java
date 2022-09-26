package pe.company.pmmcourier.service;

import java.util.Collection;

import pe.company.pmmcourier.model.ServicioDetalle;

public interface ServicioDetalleService {
	
	public abstract void insert(ServicioDetalle SD);
	public abstract void update(ServicioDetalle SD);
	public abstract void delete(Integer sdID);
	public abstract ServicioDetalle findById(Integer sdID);
	public abstract Collection<ServicioDetalle> findAll();

}
