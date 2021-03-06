package com.mikaelsonbraz.cursomc.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mikaelsonbraz.cursomc.domain.Categoria;
import com.mikaelsonbraz.cursomc.services.CategoriaService;

@RestController
@RequestMapping("/categorias")
public class CategoriaResource {
	
	@Autowired
	private CategoriaService categoriaService;
	
	@RequestMapping(method=RequestMethod.GET)
	public List<Categoria> listarTodos(){
		return categoriaService.buscarTodos();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<?> listar(@PathVariable Integer id) {
		
		Categoria obj = categoriaService.buscar(id);
		return ResponseEntity.ok().body(obj);
	}
	
}
