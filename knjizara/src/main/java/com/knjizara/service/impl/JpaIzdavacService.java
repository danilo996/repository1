package com.knjizara.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.knjizara.model.Izdavac;
import com.knjizara.repository.IzdavacRepository;
import com.knjizara.service.IzdavacService;

@Service
public class JpaIzdavacService implements IzdavacService {
	
	
	@Autowired
	private IzdavacRepository izdavacRepo;

	@Override
	public Izdavac findOne(Long id) {
		// TODO Auto-generated method stub
		return izdavacRepo.findOne(id);
	}

	@Override
	public List<Izdavac> findAll() {
		// TODO Auto-generated method stub
		return izdavacRepo.findAll();
	}

	@Override
	public Page<Izdavac> findAll(int pageNum) {
		// TODO Auto-generated method stub
		return izdavacRepo.findAll(new PageRequest(pageNum, 5));
	}

	@Override
	public Izdavac save(Izdavac izdavac) {
		// TODO Auto-generated method stub
		return izdavacRepo.save(izdavac);
	}

	@Override
	public List<Izdavac> save(List<Izdavac> izdavaci) {
		// TODO Auto-generated method stub
		return izdavacRepo.save(izdavaci);
	}

	@Override
	public void delete(Long id) {
		izdavacRepo.delete(id);
		
	}

	/*
	@Override
	public List<Izdavac> findByKnjigaId(Long knjigaId) {
		return izdavacRepo.findByKnjigaId(knjigaId);
	}
	*/

}
