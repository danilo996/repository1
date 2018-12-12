package com.knjizara.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import com.knjizara.model.Knjiga;



public interface KnjigaService {
	
	Knjiga findOne(Long id);
	List<Knjiga> findAll();
	Page<Knjiga> findAll(int pageNum);
	Knjiga save(Knjiga knjiga);
	List<Knjiga> save(List<Knjiga> knjige);
	void delete(Long id);
	List<Knjiga> findByIzdavacId(Long izdavacId);
	Page<Knjiga>  pretraga( 
			@Param("naziv") String naziv,
			@Param("pisac") String pisac,
			@Param("kolicina") Integer kolicina, int page);

}
