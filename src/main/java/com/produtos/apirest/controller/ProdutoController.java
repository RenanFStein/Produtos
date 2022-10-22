package com.produtos.apirest.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import com.produtos.apirest.models.Produto;
import com.produtos.apirest.repository.ProdutoRepository;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;


@RestController
@RequestMapping(value="/api")
@Api(tags = "Produto", description = "End-Point")
public class ProdutoController {

	@Autowired
	ProdutoRepository produtoRepository;

	// Interceptador de Erros para retornar mensagem em caso de erro.
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationException(MethodArgumentNotValidException ex){
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach(error -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});
		return errors;
	}

	// GET para listar todos os produtos.
	@ApiOperation(value="Retorna uma lista de Produtos")
	@GetMapping("/produtos")
	public ResponseEntity<List<Produto>> listaProdutos(){
		List<Produto> produto = produtoRepository.findAll();
		if (!produto.isEmpty()){
			return ResponseEntity.ok(produto);
		}
		return ResponseEntity.noContent().build();
	}

	// GET para listar produto por ID.
	@ApiOperation(value="Retorna um produto unico")
	@GetMapping("/produto/{id}")
	public ResponseEntity<Produto> listaProdutoUnico(@PathVariable(value="id") long id){
		Produto produto = produtoRepository.findById(id);
		if (produto != null){
			return ResponseEntity.ok(produto);
		}
		return ResponseEntity.noContent().build();
	}

	// POST para gravar novo produto.
	@ApiOperation(value="Salva um produto")
	@PostMapping("/produto")
	public ResponseEntity<Produto> salvaProduto(@RequestBody @Valid Produto produto) {
		Produto novoCarro = produtoRepository.save(produto);
		return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(novoCarro.getId()).toUri()).build();
	}

	// Delete - Excluir item por ID.
	@ApiOperation(value="Deleta um produto")
	@DeleteMapping("/produto/{id}")
	public ResponseEntity<Void> deletaProduto(Long id) {
		Optional<Produto> produto = produtoRepository.findById(id);
		if (produto.isPresent()){
			produtoRepository.deleteById(id);
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}

	// PUT - Atualizar item por ID.
	@ApiOperation(value="Atualiza um produto")
	@PutMapping("/produto/{id}")
	public ResponseEntity<Void> atualizaProduto(Long id, @RequestBody @Valid Produto produto) {
		Optional<Produto> atualizaProduto = produtoRepository.findById(id);
		if (atualizaProduto.isPresent()){
			produtoRepository.save(produto);
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}
}
