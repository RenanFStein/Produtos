package com.produtos.apirest.models;



import org.hibernate.validator.constraints.Range;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;


@Entity
@Table(name="TB_PRODUTO")
public class Produto implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	@NotBlank(message = "Campo não informado.")
	@Pattern(regexp = "^[A-Z]+(.)*", message = "A primeira letra deve ser Maiuscula")
	private String nome;

	@NotNull(message = "Campo não informado.")
	private BigDecimal quantidade;

	@NotNull(message = "Campo não informado.")
	@Min(value = 0, message = "Valor deve ser maior que 0.")
	private BigDecimal valor;


	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public BigDecimal getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(BigDecimal quantidade) {
		this.quantidade = quantidade;
	}
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}



}
