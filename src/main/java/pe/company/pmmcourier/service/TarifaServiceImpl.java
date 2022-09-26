package pe.company.pmmcourier.service;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.company.pmmcourier.model.Tarifa;
import pe.company.pmmcourier.repository.TarifaRepository;

@Service
public class TarifaServiceImpl implements TarifaService {
	
	@Autowired
	private TarifaRepository repo;

	@Override
	@Transactional
	public void insert(Tarifa tarifa) {
		repo.save(tarifa);

	}

	@Override
	@Transactional
	public void update(Tarifa tarifa) {
		repo.save(tarifa);

	}

	@Override
	@Transactional
	public void delete(Integer tarifaID) {
		repo.deleteById(tarifaID);

	}

	@Override
	@Transactional
	public Tarifa findById(Integer tarifaID) {
		return repo.findById(tarifaID).orElse(null);
	}

	@Override
	@Transactional
	public Collection<Tarifa> findAll() {
		return (Collection<Tarifa>) repo.findAll();
	}

}
