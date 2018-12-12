package com.knjizara;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.knjizara.model.Izdavac;
import com.knjizara.model.Knjiga;
import com.knjizara.service.IzdavacService;
import com.knjizara.service.KnjigaService;

@Component
public class TestData {
	
	@Autowired
	private KnjigaService knjigaService;
	@Autowired
	private IzdavacService izdavacService;
	
	@PostConstruct
	public void init(){
		
		Knjiga knjiga1 = new Knjiga(1L, "The Bridge on The Drina", "Ivo Andric", "aaaaaaaaaaaaaaaaaa", 10, 1000f);
		Knjiga knjiga2 = new Knjiga(2L, "Signs by The Road", "Ivo Andric", "bbbbbbbbbbbbbbbbbb", 290, 2000f);
		Knjiga knjiga3 = new Knjiga(3L, "Beogradski Trio", "Goran Markovic", "cccccccccccccccccc", 130, 11111112f);
		
		knjigaService.save(knjiga1);
		knjigaService.save(knjiga2);
		knjigaService.save(knjiga3);
		
		
		Izdavac izdavac1 = new Izdavac(1L, "Medijunm", "medijum-info.com", "0652346655");
		izdavac1.addKnjiga(knjiga1);
		izdavac1.addKnjiga(knjiga2);
		izdavac1.addKnjiga(knjiga3);
		Izdavac izdavac2 = new Izdavac(2L, "Sirmium", "sirmium-info.com", "022650230");
		Izdavac izdavac3 = new Izdavac(3L, "sid", "sid-info.com", "022321654");
		
		izdavacService.save(izdavac1);
		izdavacService.save(izdavac2);
		izdavacService.save(izdavac3);
	}

}
