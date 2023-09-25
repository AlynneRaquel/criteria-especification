package com.example.criteriaespecifications.domain.repository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.example.criteriaespecifications.domain.model.Produto;

public interface ProdutoRepositoryQueries {
	
	List<Produto> findComEstoque(String nome);
	
	List<Produto> findSemEstoque(String nome);
	
	List<Produto> findComValidade(Date dataValidade);
	
	List<Produto> findComFaixaPreco(BigDecimal precoInicial, BigDecimal precoFinal);
	
	List<Produto> findComMarcaIgual(String marca);

}
