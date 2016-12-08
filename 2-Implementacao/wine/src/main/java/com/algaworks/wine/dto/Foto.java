package com.algaworks.wine.dto;

//DTO = Data Transfer Object. 
//Usado quando quiser trafegar objetos em formatos que não são do dominio
//É um objeto de tranferencia entre as camadas ( Quando quiser mandar pro javascritp )
public class Foto {

	private String url;

	public Foto(String url) {
		this.url = url;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
