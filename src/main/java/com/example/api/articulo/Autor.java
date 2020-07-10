package com.example.api.articulo;

public class Autor {
	
	private String titulo;
	private String nombre;
	private String twiter;
	private String blog;
	private Long id;
	private Long idArt;

	
	public Autor() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Autor(Long id, String nombre, String twiter, String blog,  Long idArt) {
		super();
		this.nombre = nombre;
		this.twiter = twiter;
		this.blog = blog;
		this.id = id;
		this.idArt = idArt;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTwiter() {
		return twiter;
	}

	public void setTwiter(String twiter) {
		this.twiter = twiter;
	}

	public String getBlog() {
		return blog;
	}

	public void setBlog(String blog) {
		this.blog = blog;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdArt() {
		return idArt;
	}

	public void setIdArt(Long idArt) {
		this.idArt = idArt;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}


}
