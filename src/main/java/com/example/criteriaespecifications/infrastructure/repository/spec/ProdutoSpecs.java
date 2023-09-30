package com.example.criteriaespecifications.infrastructure.repository.spec;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.Date;

import org.springframework.data.jpa.domain.Specification;

import com.example.criteriaespecifications.domain.model.Produto;

public class ProdutoSpecs {
	
	public static Specification<Produto> comNomeSemelhante(String nome){
		return (root, query, builder) ->
		builder.like(root.get("nome"), "%" + nome + "%");
	}
	
	public static Specification<Produto> comEstoque() {
		return (root, query, builder) ->
		builder.greaterThan(root.get("quantidade"), BigInteger.ZERO);
	}
	
	public static Specification<Produto> semEstoque() {
		return (root, query, builder) ->
		builder.equal(root.get("quantidade"), BigInteger.ZERO);
	}
	
	public static Specification<Produto> comValidade(LocalDate dataValidade ){
		return (root, query, builder) ->
		builder.greaterThan(root.get("validade"), dataValidade);
	}
	
	public static Specification<Produto> comFaixaPreco(BigDecimal precoInicial, BigDecimal precoFinal){
		return(root, query, builder)->
		builder.between(root.get("preco"), precoInicial, precoFinal);
	}

	public static Specification<Produto> comMarcaigual(String marca){
		return (root, query, builder)->
		builder.equal(root.get("marca"), marca);
	}
	
	
}
