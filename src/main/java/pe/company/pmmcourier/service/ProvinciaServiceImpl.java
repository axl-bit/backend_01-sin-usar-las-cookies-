package pe.company.pmmcourier.service;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.company.pmmcourier.model.Provincia;
import pe.company.pmmcourier.repository.ProvinciaRepository;

@Service
public class ProvinciaServiceImpl implements ProvinciaService {
	
	@Autowired
	private ProvinciaRepository repo;

	@Override
	@Transactional
	public void insert(Provincia prov) {
		repo.save(prov);

	}

	@Override
	@Transactional
	public void update(Provincia prov) {
		repo.save(prov);

	}

	@Override
	@Transactional
	public void delete(Integer provID) {
		repo.deleteById(provID);

	}

	@Override
	@Transactional
	public Provincia findById(Integer provID) {
		return repo.findById(provID).orElse(null);
	}

	@Override
	@Transactional
	public Collection<Provincia> findAll() {
		return (Collection<Provincia>) repo.findAll();
	}

}

