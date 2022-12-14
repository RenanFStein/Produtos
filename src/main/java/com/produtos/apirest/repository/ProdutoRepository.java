package com.produtos.apirest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.produtos.apirest.models.Produto;

import java.util.Optional;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{
	Produto findById(long id);


}
