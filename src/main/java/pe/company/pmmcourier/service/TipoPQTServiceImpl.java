package pe.company.pmmcourier.service;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.company.pmmcourier.model.TipoPQT;
import pe.company.pmmcourier.repository.TipoPQTRepository;

@Service
public class TipoPQTServiceImpl implements TipoPQTService {
	
	@Autowired
	private TipoPQTRepository repo;

	@Override
	@Transactional
	public void insert(TipoPQT tipoPQT) {
		repo.save(tipoPQT);

	}

	@Override
	@Transactional
	public void update(TipoPQT tipoPQT) {
		repo.save(tipoPQT);

	}

	@Override
	@Transactional
	public void delete(Integer tipoPQTID) {
		repo.deleteById(tipoPQTID);

	}

	@Override
	@Transactional
	public TipoPQT findById(Integer tipoPQTID) {
		return repo.findById(tipoPQTID).orElse(null);
	}

	@Override
	@Transactional
	public Collection<TipoPQT> findAll() {
		return (Collection<TipoPQT>) repo.findAll();
	}

}
