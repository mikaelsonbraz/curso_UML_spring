package com.mikaelsonbraz.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mikaelsonbraz.cursomc.domain.Cliente;
import com.mikaelsonbraz.cursomc.repositories.ClienteRepository;
import com.mikaelsonbraz.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	public List<Cliente> buscarTodos(){
		return clienteRepository.findAll();
	}
	
	public Cliente buscar(Integer id) {
		Optional<Cliente> obj = clienteRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Cliente não encontrado! id: " + id + "tipo: " + Cliente.class.getName(), null));
	}

}
