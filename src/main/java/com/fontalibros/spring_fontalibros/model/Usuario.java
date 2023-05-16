package com.fontalibros.spring_fontalibros.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;


// Anotaciones JPA para el mapeo de las clases
@Entity
@Table(name = "usuarios")

public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nombre;
	private String apellido;
	private String documento;
	private String correo;
	private String direccion;
	private String telefono;
	private String password;
	private String tipo; // tipo de usuario administrador y usuario
	
	// Creamos un nuevo atributo que devuelva una lista de productos, utilizando la anotacion JPA para su relación
	 
	@OneToMany(mappedBy = "usuario")
	private List<Libro> libros;
	
	// Creamos un nuevo atributo que devuelva una lista de ordenes
	@OneToMany(mappedBy = "usuario")
	private List<Orden> ordenes;
	
	// Constructor vacio
	public Usuario() {
		
	}
	
	
	// Constructor con todos los campos
	public Usuario(Integer id, String nombre, String apellido, String documento, String correo, String direccion,
			String telefono, String password, String tipo) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.documento = documento;
		this.correo = correo;
		this.direccion = direccion;
		this.telefono = telefono;
		this.password = password;
		this.tipo = tipo;
	}
	
	
	// Generando los Getters y Setters
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getDocumento() {
		return documento;
	}
	public void setDocumento(String documento) {
		this.documento = documento;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public List<Libro> getLibros() {
		return libros;
	}

	public void setLibros(List<Libro> libros) {
		this.libros = libros;
	}


	// Generando el metodo toString
	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", documento=" + documento
				+ ", correo=" + correo + ", direccion=" + direccion + ", telefono=" + telefono + ", password="
				+ password + ", tipo=" + tipo + "]";
	}
	
	
}
