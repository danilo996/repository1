package com.knjizara.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.knjizara.model.Izdavac;
import com.knjizara.web.dto.IzdavacDTO;

@Component
public class IzdavacTOIzdavacDTO implements Converter<Izdavac, IzdavacDTO>{
	
	
	@Override
	public IzdavacDTO convert(Izdavac izdavac) {
		if(izdavac ==null){
			return null;
		}
		
		IzdavacDTO dto = new IzdavacDTO();
		
		dto.setId(izdavac.getId());
		dto.setNaziv(izdavac.getNaziv());
		dto.setEmail(izdavac.getEmail());
		dto.setTelefon(izdavac.getTelefon());
		
		
		return dto;
	}
	
	public List<IzdavacDTO> convert(List<Izdavac> izdavaci){
		List<IzdavacDTO> ret = new ArrayList<>();
		
		for(Izdavac izdavac: izdavaci){
			ret.add(convert(izdavac));
		}
		
		return ret;
	}
}
