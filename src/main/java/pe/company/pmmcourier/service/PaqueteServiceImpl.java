package pe.company.pmmcourier.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.company.pmmcourier.model.Paquete;
import pe.company.pmmcourier.repository.PaqueteRepository;

@Service
public class PaqueteServiceImpl implements PaqueteService {
	
	@Autowired
	private PaqueteRepository repo;

	public PaqueteServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void insert(Paquete pqt) {
		repo.save(pqt);

	}

	@Override
	public void update(Paquete pqt) {
		repo.save(pqt);

	}

	@Override
	public void delete(Integer pqtID) {
		repo.deleteById(pqtID);

	}

	@Override
	public Paquete findById(Integer pqtID) {
		return repo.findById(pqtID).orElse(null);
	}

	@Override
	public Collection<Paquete> findAll() {
		return (Collection<Paquete>) repo.findAll();
	}

}
