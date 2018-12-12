package com.knjizara.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="izdavaci")
public class Izdavac {
	
	

	@Id
	@GeneratedValue
	@Column(name="id")
	private Long id;
	@Column(unique=true, nullable=false)
	private String naziv;
	@Column
	private String email;
	@Column(nullable=false)
	private String telefon;
	@OneToMany(mappedBy = "izdavac" ,fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	private List<Knjiga> knjige = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefon() {
		return telefon;
	}

	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}
	
	public List<Knjiga> getKnjige() {
		return knjige;
	}

	public void setKnjige(List<Knjiga> knjige) {
		this.knjige = knjige;
	}

	public void addKnjiga(Knjiga knjiga){
		this.knjige.add(knjiga);
		if(knjiga.getIzdavac()!=this){
			knjiga.setIzdavac(this);
		}
	}
	public Izdavac(Long id, String naziv, String email, String telefon) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.email = email;
		this.telefon = telefon;
	}

	public Izdavac() {
		super();
	}
	
	
	

}
