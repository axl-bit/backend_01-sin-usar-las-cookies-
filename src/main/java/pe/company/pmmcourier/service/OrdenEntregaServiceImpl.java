package pe.company.pmmcourier.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.company.pmmcourier.model.OrdenEntrega;
import pe.company.pmmcourier.repository.OrdenEntregaRepository;

@Service
public class OrdenEntregaServiceImpl implements OrdenEntregaService {
	
	@Autowired
	private OrdenEntregaRepository repo;

	public OrdenEntregaServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void insert(OrdenEntrega OE) {
		repo.save(OE);

	}

	@Override
	public void update(OrdenEntrega OE) {
		repo.save(OE);

	}

	@Override
	public void delete(Integer oeID) {
		repo.deleteById(oeID);
	}

	@Override
	public OrdenEntrega findById(Integer oeID) {
		return repo.findById(oeID).orElse(null);
	}

	@Override
	public Collection<OrdenEntrega> findAll() {
		return (Collection<OrdenEntrega>) repo.findAll();
	}

}
