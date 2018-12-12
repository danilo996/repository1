package com.knjizara.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="knjige")
public class Knjiga {
	
	@Id
	@GeneratedValue
	@Column(name="id")
	private Long id;
	public Knjiga() {
		super();
	}

	@Column(unique=true, nullable=false)
	private String naziv;
	public Knjiga(Long id, String naziv, String pisac, String isbn, Integer kolicina, Float cena) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.pisac = pisac;
		this.isbn = isbn;
		this.kolicina = kolicina;
		this.cena = cena;
	}

	@Column
	private String pisac;
	@Column
	private String isbn;
	@Column
	private Integer kolicina;
	@Column
	private Float cena;
	
	
	@ManyToOne
	private Izdavac izdavac;

	

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

	public String getPisac() {
		return pisac;
	}

	public void setPisac(String pisac) {
		this.pisac = pisac;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public Integer getKolicina() {
		return kolicina;
	}

	public void setKolicina(Integer kolicina) {
		this.kolicina = kolicina;
	}

	public Float getCena() {
		return cena;
	}

	public void setCena(Float cena) {
		this.cena = cena;
	}
	public Izdavac getIzdavac() {
		return izdavac;
	}

	public void setIzdavac(Izdavac izdavac) {
		this.izdavac = izdavac;
		if(izdavac!=null && !izdavac.getKnjige().contains(this)){
			izdavac.getKnjige().add(this);
		}
	}

	
}
