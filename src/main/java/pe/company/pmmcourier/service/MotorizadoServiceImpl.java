package pe.company.pmmcourier.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.company.pmmcourier.model.Motorizado;
import pe.company.pmmcourier.repository.MotorizadoRepository;

@Service
public class MotorizadoServiceImpl implements MotorizadoService {
	
	@Autowired
	private MotorizadoRepository repo;

	public MotorizadoServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void insert(Motorizado moto) {
		repo.save(moto);

	}

	@Override
	public void update(Motorizado moto) {
		repo.save(moto);

	}

	@Override
	public void delete(Integer motoID) {
		repo.deleteById(motoID);

	}

	@Override
	public Motorizado findById(Integer motoID) {
		return repo.findById(motoID).orElse(null);
	}

	@Override
	public Collection<Motorizado> findAll() {
		return (Collection<Motorizado>) repo.findAll();
	}

}
