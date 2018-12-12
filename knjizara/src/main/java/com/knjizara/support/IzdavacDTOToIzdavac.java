package com.knjizara.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.knjizara.model.Izdavac;
import com.knjizara.service.IzdavacService;
import com.knjizara.web.dto.IzdavacDTO;

@Component
public class IzdavacDTOToIzdavac
implements Converter<IzdavacDTO, Izdavac>{
	
@Autowired IzdavacService izdavacService;
	
	@Override
	public Izdavac convert(IzdavacDTO dto) {
		Izdavac izdavac = new Izdavac();
		
		if(dto.getId()!=null){
			izdavac = izdavacService.findOne(dto.getId());
			
			if(izdavac == null){
				throw new IllegalStateException("Tried to "
						+ "modify a non-existant activity");
			}
		}
		
		izdavac.setId(dto.getId());
		izdavac.setNaziv(dto.getNaziv());
		izdavac.setEmail(dto.getEmail());
		izdavac.setTelefon(dto.getTelefon());
		
		return izdavac;
	}
	
	public List<Izdavac> convert (List<IzdavacDTO> dtoIzdavac){
		List<Izdavac> izdavaci = new ArrayList<>();
		
		for(IzdavacDTO dto : dtoIzdavac){
			izdavaci.add(convert(dto));
		}
		
		return izdavaci;
	}

}
