package com.knjizara.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.knjizara.model.Knjiga;
import com.knjizara.service.IzdavacService;
import com.knjizara.service.KnjigaService;
import com.knjizara.web.dto.KnjigaDTO;


@Component
public class KnjigaDTOToKnjiga
implements Converter<KnjigaDTO, Knjiga>{
	
@Autowired KnjigaService knjigaService;
@Autowired IzdavacService izdavacService;
	
	@Override
	public Knjiga convert(KnjigaDTO dto) {
		Knjiga knjiga = new Knjiga();
		

		if(dto.getId()==null) {
			knjiga = new Knjiga();
			knjiga.setIzdavac(izdavacService.findOne(dto.getIzdavacId())); 
		}else {
			knjiga = knjigaService.findOne(dto.getId());
		}
		knjiga.setIzdavac(izdavacService.findOne(dto.getIzdavacId()));
		knjiga.setNaziv(dto.getNaziv());
		knjiga.setPisac(dto.getPisac());
		knjiga.setIsbn(dto.getIsbn());
		knjiga.setCena(dto.getCena());
		knjiga.setKolicina(dto.getKolicina());
		
		return knjiga;
	}
	
	public List<Knjiga> convert (List<KnjigaDTO> dtoKnjige){
		List<Knjiga> knjige = new ArrayList<>();
		
		for(KnjigaDTO dto : dtoKnjige){
			knjige.add(convert(dto));
		}
		
		return knjige;
	}

}
