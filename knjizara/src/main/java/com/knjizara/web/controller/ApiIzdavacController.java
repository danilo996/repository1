package com.knjizara.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.knjizara.model.Izdavac;
import com.knjizara.model.Knjiga;
import com.knjizara.service.IzdavacService;
import com.knjizara.service.KnjigaService;
import com.knjizara.support.IzdavacTOIzdavacDTO;
import com.knjizara.support.KnjigaToKnjigaDTO;
import com.knjizara.web.dto.IzdavacDTO;
import com.knjizara.web.dto.KnjigaDTO;


@RestController
@RequestMapping(value = "/api/izdavaci")
public class ApiIzdavacController {
	
	@Autowired
	private IzdavacService izdavacService;
	@Autowired
	private KnjigaService knjigaService;
	@Autowired 
	private IzdavacTOIzdavacDTO toDto;
	@Autowired
	private KnjigaToKnjigaDTO toKnjigaDTO;
	
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<IzdavacDTO>> getAll() {

		List<Izdavac> izdavaci = izdavacService.findAll();

		return new ResponseEntity<>(toDto.convert(izdavaci), HttpStatus.OK);
	}
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	ResponseEntity<IzdavacDTO> getIzdavac(@PathVariable Long id) {
		Izdavac izdavac = izdavacService.findOne(id);
		if (izdavac == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(toDto.convert(izdavac), HttpStatus.OK);
	}
	@RequestMapping(method = RequestMethod.GET, value="/{id}/knjige")
	public ResponseEntity<List<KnjigaDTO>> get(@PathVariable Long id) {

		List<Knjiga> knjige = knjigaService.findByIzdavacId(id);

		return new ResponseEntity<>(toKnjigaDTO.convert(knjige), HttpStatus.OK);
	}
}
