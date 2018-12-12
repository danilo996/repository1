package com.knjizara.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.knjizara.model.Izdavac;

public interface IzdavacService {
	
	
	Izdavac findOne(Long id);
	List<Izdavac> findAll();
	Page<Izdavac> findAll(int pageNum);
	Izdavac save(Izdavac izdavac);
	List<Izdavac> save(List<Izdavac> izdavaci);
	void delete(Long id);
	//List<Izdavac> findByKnjigaId(Long knjigaId);

}
