package com.mikaelsonbraz.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mikaelsonbraz.cursomc.domain.Categoria;
import com.mikaelsonbraz.cursomc.repositories.CategoriaRepository;
import com.mikaelsonbraz.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public List<Categoria> buscarTodos(){
		return categoriaRepository.findAll();
	}
	
	public Categoria buscar(Integer id) {
		Optional<Categoria> obj = categoriaRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", tipo: " + Categoria.class.getName(), null));
	}

}
