package pe.company.pmmcourier.service;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.company.pmmcourier.model.TipoColaborador;
import pe.company.pmmcourier.repository.TipoColaboradorRepository;

@Service
public class TipoColaboradorServiceImpl implements TipoColaboradorService {

	@Autowired
	private TipoColaboradorRepository repo;
	
	
	@Override
	public void insert(TipoColaborador Tipo_Colaborador) {
		repo.save(Tipo_Colaborador);

	}

	@Override
	@Transactional
	public void update(TipoColaborador Tipo_Colaborador) {
		repo.save(Tipo_Colaborador);

	}

	@Override
	@Transactional
	public void delete(Integer TipoCID) {
		repo.deleteById(TipoCID);

	}

	@Override
	@Transactional
	public TipoColaborador findById(Integer TipoCID) {
		return repo.findById(TipoCID).orElse(null);
	}

	@Override
	@Transactional
	public Collection<TipoColaborador> findAll() {
		return (Collection<TipoColaborador>) repo.findAll();
	}

}
