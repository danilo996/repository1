package com.knjizara.web.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.knjizara.model.Knjiga;
import com.knjizara.service.KnjigaService;
import com.knjizara.support.KnjigaDTOToKnjiga;
import com.knjizara.support.KnjigaToKnjigaDTO;
import com.knjizara.web.dto.KnjigaDTO;

@RestController
@RequestMapping(value = "/api/knjige")
public class ApiKnjigaController {
	
	
	@Autowired
	private KnjigaService knjigaService;
	@Autowired 
	private KnjigaToKnjigaDTO toDto;
	@Autowired
	private KnjigaDTOToKnjiga toKnjiga;
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	ResponseEntity<KnjigaDTO> getFestival(@PathVariable Long id) {
		Knjiga knjiga = knjigaService.findOne(id);
		if (knjiga == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(toDto.convert(knjiga), HttpStatus.OK);
	}
	
	// SVI
	/*
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<KnjigaDTO>> get() {

		List<Knjiga> knjige = knjigaService.findAll();

		return new ResponseEntity<>(toDto.convert(knjige), HttpStatus.OK);
	}
	*/
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<KnjigaDTO>> getAll(@RequestParam(required = false) String naziv,
			@RequestParam(required = false) String pisac,
			@RequestParam(required = false) Integer kolicina,
			@RequestParam(defaultValue = "0") int pageNum) {

		Page<Knjiga> knjige;
		HttpHeaders headers = new HttpHeaders();
		if (naziv != null || pisac != null || kolicina != null) {
			knjige = knjigaService.pretraga(naziv, pisac, kolicina, pageNum);
		} else {
			knjige = knjigaService.findAll(pageNum);
		}

		
		headers.add("totalPages", Integer.toString(knjige.getTotalPages()));
		return new ResponseEntity<>(toDto.convert(knjige.getContent()), headers, HttpStatus.OK);
	}
	
	
	
	// DODAJ
	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<KnjigaDTO> add( @RequestBody KnjigaDTO newKnjiga) {

		Knjiga savedKnjiga = knjigaService.save(toKnjiga
				.convert(newKnjiga));

		return new ResponseEntity<>(toDto.convert(savedKnjiga), HttpStatus.CREATED);
	}
	// IZBRISI VOID
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	ResponseEntity<KnjigaDTO> delete(@PathVariable Long id) {
		knjigaService.delete(id);

		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	//IZMENI
	@RequestMapping(method = RequestMethod.PUT, value = "/{id}", consumes = "application/json")
	public ResponseEntity<KnjigaDTO> edit( @RequestBody KnjigaDTO knjiga,
			@PathVariable Long id) {

		if (id != knjiga.getId()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		Knjiga persisted = knjigaService.save(toKnjiga.convert(knjiga));

		return new ResponseEntity<>(toDto.convert(persisted), HttpStatus.OK);
	}
	
	/*
	@RequestMapping(method=RequestMethod.POST, value="/{id}/kupi/{brojKarata}")
	public ResponseEntity<KupovinaDTO> rent(@PathVariable Long id, @PathVariable Integer brojKarata){
		System.out.println(brojKarata);
		System.out.println(brojKarata);
		System.out.println(brojKarata);
		System.out.println(brojKarata);
		Kupovina k = kupovinaService.kupiKartu(id, brojKarata);
		
		if(k != null) {
			return new ResponseEntity<>(toKupovinaDTO.convert(k), HttpStatus.CREATED);
		}
		else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	@ExceptionHandler
	public ResponseEntity<Void> validationHandler(
					DataIntegrityViolationException e){
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
}
	
	
	
	/*
	//EXCEPTION HANDLER
	@ExceptionHandler(value=DataIntegrityViolationException.class)
	public ResponseEntity<Void> handle() {
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	*/
}
