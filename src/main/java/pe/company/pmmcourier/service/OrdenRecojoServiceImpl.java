package pe.company.pmmcourier.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.company.pmmcourier.model.OrdenRecojo;
import pe.company.pmmcourier.repository.OrdenRecojoRepository;

@Service
public class OrdenRecojoServiceImpl implements OrdenRecojoService {
	
	@Autowired
	private OrdenRecojoRepository repo;

	public OrdenRecojoServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void insert(OrdenRecojo OR) {
		repo.save(OR);

	}

	@Override
	public void update(OrdenRecojo OR) {
		repo.save(OR);

	}

	@Override
	public void delete(Integer orID) {
		repo.deleteById(orID);

	}

	@Override
	public OrdenRecojo findById(Integer orID) {
		return repo.findById(orID).orElse(null);
	}

	@Override
	public Collection<OrdenRecojo> findAll() {
		return (Collection<OrdenRecojo>) repo.findAll();
	}

}
