package com.autozone.models;

import com.autozone.annotations.Nombre_s;
import com.autozone.annotations.NotNull;
import com.autozone.annotations.telef;

public class Miembros {
	
	/**En esta clase se declaran las variables, se aplican anotaciones
	 *se crean metodos getter y setters y se crea un metodo constructor para
	 *usarse como base a la hora de crear un objeto de esta clase.
	 */
	
	private int id;
	@NotNull
	@Nombre_s
	private String nombre;
	@NotNull
	@telef
	private String telefono;
	
	public Miembros(String nombre, String telefono) {
		super();
		this.nombre = nombre;
		this.telefono = telefono;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
}
