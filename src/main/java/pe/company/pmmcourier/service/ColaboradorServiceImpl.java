package pe.company.pmmcourier.service;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.company.pmmcourier.model.Colaborador;
import pe.company.pmmcourier.repository.ColaboradorRespository;


@Service
public class ColaboradorServiceImpl implements ColaboradorService {
	
	@Autowired
	private ColaboradorRespository repo;

	@Override
	@Transactional
	public void insert(Colaborador colab) {
		repo.save(colab);

	}

	@Override
	@Transactional
	public void update(Colaborador colab) {
		repo.save(colab);

	}

	@Override
	@Transactional
	public void delete(Integer colabID) {
		repo.deleteById(colabID);

	}

	@Override
	@Transactional
	public Colaborador findById(Integer colabID) {
		return repo.findById(colabID).orElse(null);
	}

	@Override
	@Transactional
	public Collection<Colaborador> findAll() {
		return (Collection<Colaborador>) repo.findAll();
	}

}
