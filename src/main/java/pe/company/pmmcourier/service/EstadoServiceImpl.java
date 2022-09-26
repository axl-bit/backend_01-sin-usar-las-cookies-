package pe.company.pmmcourier.service;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.company.pmmcourier.model.Estado;
import pe.company.pmmcourier.repository.EstadoRepository;

@Service
public class EstadoServiceImpl implements EstadoService {
	
	@Autowired
	private EstadoRepository repo;

	@Override
	@Transactional
	public void insert(Estado est) {
		repo.save(est);

	}

	@Override
	@Transactional
	public void update(Estado est) {
		repo.save(est);

	}

	@Override
	@Transactional
	public void delete(Integer estID) {
		repo.deleteById(estID);

	}

	@Override
	@Transactional
	public Estado findById(Integer estID) {
		return repo.findById(estID).orElse(null);
	}

	@Override
	@Transactional
	public Collection<Estado> findAll() {
		return (Collection<Estado>) repo.findAll();
	}

}
