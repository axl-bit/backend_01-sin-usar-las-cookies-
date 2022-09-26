package pe.company.pmmcourier.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.company.pmmcourier.model.Zona;
import pe.company.pmmcourier.repository.ZonaRepository;

@Service
public class ZonaServiceImpl implements ZonaService {
	
	@Autowired
	private ZonaRepository repo;

	public ZonaServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void insert(Zona zona) {
		repo.save(zona);

	}

	@Override
	public void update(Zona zona) {
		repo.save(zona);

	}

	@Override
	public void delete(Integer zonaID) {
		repo.deleteById(zonaID);

	}

	@Override
	public Zona findById(Integer zonabID) {
		return repo.findById(zonabID).orElse(null);
	}

	@Override
	public Collection<Zona> findAll() {
		return (Collection<Zona>) repo.findAll();
	}

}
