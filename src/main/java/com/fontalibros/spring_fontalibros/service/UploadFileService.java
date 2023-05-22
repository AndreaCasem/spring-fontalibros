package com.fontalibros.spring_fontalibros.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

// Clase para subir imágenes de un libro o eliminarlas

@Service
public class UploadFileService {
	private String folder="images//";
	
	// Guardar imagen y transformarla a bytes
	public String saveImage(MultipartFile file) throws IOException {
		
		if (!file.isEmpty() ) {
			byte [] bytes = file.getBytes();
			Path path = Paths.get(folder + file.getOriginalFilename());
			Files.write(path, bytes);
			return file.getOriginalFilename();
		}
		return "default.jpg";
	}
	
	// Eliminar imagen
	public void deleteImage(String nombre) {
		String ruta = "images//";
		File file = new File(ruta + nombre);
		file.delete();
		
	}
}
