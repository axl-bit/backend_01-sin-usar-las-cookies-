package pe.company.pmmcourier.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.company.pmmcourier.model.ServicioDetalle;
import pe.company.pmmcourier.repository.ServicioDetalleRespository;

@Service
public class ServicioDetalleServiceImpl implements ServicioDetalleService {
	
	@Autowired
	private ServicioDetalleRespository repo;

	public ServicioDetalleServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void insert(ServicioDetalle SD) {
		repo.save(SD);

	}

	@Override
	public void update(ServicioDetalle SD) {
		repo.save(SD);

	}

	@Override
	public void delete(Integer sdID) {
		repo.deleteById(sdID);

	}

	@Override
	public ServicioDetalle findById(Integer sdID) {
		return repo.findById(sdID).orElse(null);
	}

	@Override
	public Collection<ServicioDetalle> findAll() {
		return (Collection<ServicioDetalle>) repo.findAll();
	}

}
