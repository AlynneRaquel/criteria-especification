package com.example.criteriaespecifications.infrastructure.repository;

import static com.example.criteriaespecifications.infrastructure.repository.spec.ProdutoSpecs.comEstoque;
import static com.example.criteriaespecifications.infrastructure.repository.spec.ProdutoSpecs.comNomeSemelhante;
import static com.example.criteriaespecifications.infrastructure.repository.spec.ProdutoSpecs.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.example.criteriaespecifications.domain.model.Produto;
import com.example.criteriaespecifications.domain.repository.ProdutoRepository;
import com.example.criteriaespecifications.domain.repository.ProdutoRepositoryQueries;

@Repository
public class ProdutoRepositoryImpl implements ProdutoRepositoryQueries {

	@Autowired 
	@Lazy
	private ProdutoRepository produtoRepository;
	
	@Override
	public List<Produto> findComEstoque(String nome) {
		return produtoRepository.findAll(comNomeSemelhante(nome)
				.and(comEstoque()));
	}

	@Override
	public List<Produto> findSemEstoque(String nome) {
		return produtoRepository.findAll(comNomeSemelhante(nome)
				.and(semEstoque()));
	}

	@Override
	public List<Produto> findComValidade(LocalDate dataValidade) {
		return produtoRepository.findAll(comValidade(dataValidade));
	}

	@Override
	public List<Produto> findComFaixaPreco(BigDecimal precoInicial, BigDecimal precoFinal) {
		return produtoRepository.findAll(comFaixaPreco(precoInicial, precoFinal));
	}

	@Override
	public List<Produto> findComMarcaIgual(String marca) {
		return produtoRepository.findAll(comMarcaigual(marca));
	}

	@Override
	public List<Produto> getWithAllParams(String nome, BigDecimal precoInicial, BigDecimal precoFinal, String marca) {
		return produtoRepository.findAll(comNomeSemelhante(nome).
				or(comMarcaigual(marca).or(comFaixaPreco(precoInicial, precoFinal))));
	}

}
