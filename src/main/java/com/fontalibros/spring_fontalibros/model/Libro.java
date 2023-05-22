package com.fontalibros.spring_fontalibros.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

//Anotaciones JPA para el mapeo de las clases
@Entity
@Table(name = "libros")
public class Libro {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String titulo;
	private String autor;
	private String editorial;
	private String descripcion;
	private String isbn;
	private String imagenes;
	private double precio;
	private CalidadLibro calidad;
	//private CategoriaLibro categoria;
	
	// Creamos un nuevo atributo de tipo usuario y lo relacionamos con la tabla Usuario
	
	@ManyToOne
	private Usuario usuario;
	
	
	// Creacion de enum para calidad del libro
	public enum CalidadLibro {
		MUY_MALO,
		MALO,
		NORMAL,
		BUENO,
		MUY_BUENO
	}
	
	// Creacion de enum para categoria del libro
	/*
	public enum CategoriaLibro {
		EDUCACION,
		LITERATURA,
		INFANTIL
	}
	*/
	
	// Constructor vacio
	public Libro() {
		
	}
	
	
	// Constructor con todos los campos
	public Libro(Integer id, String titulo, String autor, String editorial, String descripcion, String isbn,
			String imagenes, double precio, CalidadLibro calidad, Usuario usuario) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.autor = autor;
		this.editorial = editorial;
		this.descripcion = descripcion;
		this.isbn = isbn;
		this.imagenes = imagenes;
		this.precio = precio;
		this.calidad = calidad;
		//this.categoria = categoria;
		this.usuario = usuario;
	}

	
	
	// Generando los Getters y Setters
	public Integer getId() {
		return id;
	}
	

	public void setId(Integer id) {
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


	public String getEditorial() {
		return editorial;
	}


	public void setEditorial(String editorial) {
		this.editorial = editorial;
	}


	public String getDescripcion() {
		return descripcion;
	}


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	public String getIsbn() {
		return isbn;
	}


	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	

	public String getImagenes() {
		return imagenes;
	}


	public void setImagenes(String imagenes) {
		this.imagenes = imagenes;
	}


	public double getPrecio() {
		return precio;
	}


	public void setPrecio(double precio) {
		this.precio = precio;
	}
	
	
	public Usuario getUsuario() {
		return usuario;
	}


	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

		
	// Getters para los enums creados en la clase
	public CalidadLibro getCalidad() {
		return this.calidad;
	}

	/*
	public CategoriaLibro getCategoria() {
		return this.categoria;
	}
	*/

	// Generando el metodo toString
	@Override
	public String toString() {
		return "Libro [id=" + id + ", titulo=" + titulo + ", autor=" + autor + ", editorial=" + editorial
				+ ", descripcion=" + descripcion + ", isbn=" + isbn + ", imagenes=" + imagenes + ", precio=" + precio
				+ ", calidad=" + calidad + ", usuario=" + usuario + "]";
	}
	
	
	
	
	
	
	/*
	@Override
	public String toString() {
		return "Libro [id=" + id + ", titulo=" + titulo + ", autor=" + autor + ", editorial=" + editorial
				+ ", descripcion=" + descripcion + ", isbn=" + isbn + ", imagenes=" + imagenes + ", precio=" + precio
				+ ", calidad=" + calidad + ", categoria=" + categoria + ", usuario=" + usuario + "]";
	}
	
	
	
	
	public String toString() {
		return "Libro [id=" + id + ", titulo=" + titulo + ", autor=" + autor + ", editorial=" + editorial
				+ ", descripcion=" + descripcion + ", isbn=" + isbn + ", imagenes=" + Arrays.toString(imagenes)
				+ ", precio=" + precio + ", calidad=" + calidad + ", categoria=" + categoria + "]";
	}
	*/
	
	
}
