package com.knjizara.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.knjizara.model.Knjiga;

@Repository
public interface KnjigaRepository 
	extends JpaRepository<Knjiga, Long> {
	
	
	List<Knjiga> findByIzdavacId(Long id);

	
	@Query("SELECT k FROM Knjiga k WHERE "
			+ "(:naziv IS NULL OR k.naziv = :naziv  ) AND "
			+ "(:pisac IS NULL OR k.pisac = :pisac) AND "
			+ "(:kolicina IS NULL OR k.kolicina <= :kolicina)"
			)
	Page<Knjiga> pretraga( 
			@Param("naziv") String naziv,
			@Param("pisac") String pisac,
			@Param("kolicina") Integer kolicina, Pageable pageRequest);

}
