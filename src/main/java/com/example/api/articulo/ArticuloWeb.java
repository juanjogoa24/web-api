package com.example.api.articulo;

public class ArticuloWeb {

	private Long id;
	private String titulo;
	private String autor;
	private String twiter;
	private String blog;

	public ArticuloWeb(Long id, String titulo, String autor, String twiter, String blog) {
		super();
		this.id=id;
		this.titulo = titulo;
		this.autor = autor;
		this.twiter = twiter;
		this.blog = blog;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
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

}
