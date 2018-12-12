package com.knjizara.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.knjizara.model.Knjiga;
import com.knjizara.web.dto.KnjigaDTO;
@Component
public class KnjigaToKnjigaDTO
implements Converter<Knjiga, KnjigaDTO>{
	
	
	@Override
	public KnjigaDTO convert(Knjiga knjiga) {
		if(knjiga ==null){
			return null;
		}
		
		KnjigaDTO dto = new KnjigaDTO();
		
		dto.setId(knjiga.getId());
		dto.setNaziv(knjiga.getNaziv());
		dto.setPisac(knjiga.getPisac());
		dto.setIsbn(knjiga.getIsbn());
		dto.setCena(knjiga.getCena());
		dto.setKolicina(knjiga.getKolicina());
		dto.setIzdavacId(knjiga.getIzdavac().getId());
		dto.setIzdavacNaziv(knjiga.getIzdavac().getNaziv());
		
		
		return dto;
	}
	
	public List<KnjigaDTO> convert(List<Knjiga> knjige){
		List<KnjigaDTO> ret = new ArrayList<>();
		
		for(Knjiga knjiga: knjige){
			ret.add(convert(knjiga));
		}
		
		return ret;
	}

}
