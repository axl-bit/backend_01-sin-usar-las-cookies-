package pe.company.pmmcourier.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.company.pmmcourier.model.Disponibilidad;
import pe.company.pmmcourier.repository.DisponibilidadRepository;

@Service
public class DisponibilidadServiceImpl implements DisponibilidadService {
	
	@Autowired
	private DisponibilidadRepository repo;

	public DisponibilidadServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void insert(Disponibilidad dispo) {
		repo.save(dispo);

	}

	@Override
	public void update(Disponibilidad dispo) {
		repo.save(dispo);

	}

	@Override
	public void delete(Integer dispoID) {
		repo.deleteById(dispoID);

	}

	@Override
	public Disponibilidad findById(Integer dispoID) {
		return repo.findById(dispoID).orElse(null);
	}

	@Override
	public Collection<Disponibilidad> findAll() {
		return (Collection<Disponibilidad>) repo.findAll();
	}

}
