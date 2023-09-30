package com.example.criteriaespecifications.domain.repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.example.criteriaespecifications.domain.model.Produto;

public interface ProdutoRepositoryQueries {
	
	List<Produto> findComEstoque(String nome);
	
	List<Produto> findSemEstoque(String nome);
	
	List<Produto> findComValidade(LocalDate dataValidade);
	
	List<Produto> findComFaixaPreco(BigDecimal precoInicial, BigDecimal precoFinal);
	
	List<Produto> findComMarcaIgual(String marca);
	
	List<Produto> getWithAllParams(String nome, BigDecimal precoInicial, BigDecimal precoFinal, String marca);

}
