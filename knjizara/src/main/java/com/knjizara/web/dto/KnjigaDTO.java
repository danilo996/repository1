package com.knjizara.web.dto;

public class KnjigaDTO {
	
	private Long id;
	private String naziv;
	private String pisac;
	private String isbn;
	private Integer kolicina;
	private Float cena;
	private Long izdavacId;
	private String izdavacNaziv;
	
	
	public Long getIzdavacId() {
		return izdavacId;
	}
	public void setIzdavacId(Long izdavacId) {
		this.izdavacId = izdavacId;
	}
	public String getIzdavacNaziv() {
		return izdavacNaziv;
	}
	public void setIzdavacNaziv(String izdavacNaziv) {
		this.izdavacNaziv = izdavacNaziv;
	}
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
	

}
