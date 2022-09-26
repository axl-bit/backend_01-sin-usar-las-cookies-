package pe.company.pmmcourier.service;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.company.pmmcourier.model.Distrito;
import pe.company.pmmcourier.repository.DistritoRepository;

@Service
public class DistritoServiceImpl implements DistritoService {
	
	@Autowired
	private DistritoRepository repo;

	@Override
	@Transactional
	public void insert(Distrito dist) {
		repo.save(dist);

	}

	@Override
	@Transactional
	public void update(Distrito dist) {
		repo.save(dist);

	}

	@Override
	@Transactional
	public void delete(Integer distID) {
		repo.deleteById(distID);

	}

	@Override
	@Transactional
	public Distrito findById(Integer distID) {
		return repo.findById(distID).orElse(null);
	}

	@Override
	@Transactional
	public Collection<Distrito> findAll() {
		return (Collection<Distrito>) repo.findAll();
	}

}
