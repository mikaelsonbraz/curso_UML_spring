package com.mikaelsonbraz.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mikaelsonbraz.cursomc.domain.Cidade;

public interface CidadeRepository extends JpaRepository<Cidade, Integer>{

}
