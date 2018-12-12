package com.knjizara.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.knjizara.model.Knjiga;
import com.knjizara.repository.KnjigaRepository;
import com.knjizara.service.KnjigaService;

@Service
public class JpaKnjigaService implements KnjigaService {
	
	@Autowired
	private KnjigaRepository knjigaRepo;

	@Override
	public Knjiga findOne(Long id) {
		// TODO Auto-generated method stub
		return knjigaRepo.findOne(id);
	}

	@Override
	public List<Knjiga> findAll() {
		// TODO Auto-generated method stub
		return knjigaRepo.findAll();
	}

	@Override
	public Page<Knjiga> findAll(int pageNum) {
		// TODO Auto-generated method stub
		return knjigaRepo.findAll(new PageRequest(pageNum, 5));
	}

	@Override
	public Knjiga save(Knjiga knjiga) {
		// TODO Auto-generated method stub
		return knjigaRepo.save(knjiga);
	}

	@Override
	public List<Knjiga> save(List<Knjiga> knjige) {
		// TODO Auto-generated method stub
		return knjigaRepo.save(knjige);
	}

	@Override
	public void delete(Long id) {
		knjigaRepo.delete(id);
		
	}

	@Override
	public List<Knjiga> findByIzdavacId(Long izdavacId) {
		// TODO Auto-generated method stub
		return knjigaRepo.findByIzdavacId(izdavacId);
	}

	@Override
	public Page<Knjiga> pretraga(String naziv, String pisac,Integer kolicina, int page) {
		
		return knjigaRepo.pretraga(naziv, pisac, kolicina, new PageRequest(page, 5));
	}

}
