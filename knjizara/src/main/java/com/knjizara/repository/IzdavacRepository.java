package com.knjizara.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.knjizara.model.Izdavac;

@Repository
public interface IzdavacRepository
	extends JpaRepository<Izdavac, Long>{
	
	//List<Izdavac> findByKnjigaId(Long id);

}
