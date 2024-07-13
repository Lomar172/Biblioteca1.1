package com.autozone.models;

import com.autozone.annotations.NotNull;

public class Libros {
	
	/**En esta clase se declaran las variables, se aplican anotaciones
	 *se crean metodos getter y setters y se crea un metodo constructor para
	 *usarse como base a la hora de crear un objeto de esta clase.
	 */
	
	private int lib_id;
	@NotNull
	private String isbn;
	@NotNull
	private String titulo;
	@NotNull
	private String autor;
	private String disponibilidad;

	
	
	public Libros(String isbn, String titulo, String autor, String disponibilidad) {
		super();
		this.isbn = isbn;
		this.titulo = titulo;
		this.autor = autor;
		this.disponibilidad = disponibilidad;
	}


	public int getLib_id() {
		return lib_id;
	}


	public void setLib_id(int lib_id) {
		this.lib_id = lib_id;
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

	public void setDisponibilidad(String disponibilidad) {
		this.disponibilidad = disponibilidad;
	}

	public String getDisponibilidad() {
		return disponibilidad;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
}
