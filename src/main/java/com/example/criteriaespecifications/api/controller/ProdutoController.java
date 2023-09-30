package com.example.criteriaespecifications.api.controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.criteriaespecifications.domain.model.Produto;
import com.example.criteriaespecifications.domain.repository.ProdutoRepository;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/produtos")
public class ProdutoController {

	@Autowired
	private ProdutoRepository produtoRepository;
	
	@GetMapping
	public List<Produto> listar(){
		log.info("Listando todos os produtos");
		return produtoRepository.findAll();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Produto adicionar(@Valid @RequestBody Produto produto) {
		log.info("Produto {} salvo.", produto.getId(), produto.getNome());
		return produtoRepository.save(produto);
	}
	
	@GetMapping("/{produtoId}")
	public ResponseEntity<Produto> buscar(@PathVariable Long produtoId){
		return produtoRepository.findById(produtoId)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}
	
	@PutMapping("/{produtoId}")
	public ResponseEntity<Produto> atualizar(@PathVariable Long produtoId, 
			@Valid @RequestBody Produto produto) {
		if(!produtoRepository.existsById(produtoId)) {
			log.error("Produto {} não existe, não será possível atualizar.", produtoId);
			return ResponseEntity.notFound().build();
		}
		
		produto.setId(produtoId);
		produto = produtoRepository.save(produto); 
		log.info("Produto {} atualizado.", produtoId);		
		return ResponseEntity.ok(produto);
	}

	@DeleteMapping("/{produtoId}")
	public ResponseEntity<Void> remover(@PathVariable Long produtoId) {
		if (!produtoRepository.existsById(produtoId)) {
			log.error("Produto {} não foi encontrado para deleção" , produtoId);
			return ResponseEntity.notFound().build();
		}

		produtoRepository.deleteById(produtoId);
		log.error("Produto {} deletado.", produtoId);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/com-estoque")
	public List<Produto> comEstoque(String nome){
		log.info("Listando produtos com estoque");
		return produtoRepository.findComEstoque(nome);
	}
	
	@GetMapping("/sem-estoque")
	public List<Produto> semEstoque(String nome){
		log.info("Listando produtos sem estoque");
		return produtoRepository.findSemEstoque(nome);
	}
	
	@GetMapping("/com-faixa-preco")
	public List<Produto> comFaixaPreco(BigDecimal precoInicial, BigDecimal precoFinal){
		log.info("Listando produtos com faixa de preço selecionada");
		return produtoRepository.findComFaixaPreco(precoInicial, precoFinal);
	}
	
	@GetMapping("/com-marca-igual")
	public List<Produto> comMarcaIgual(String marca){
		log.info("Listando produtos da marcar selecionada");
		return produtoRepository.findComMarcaIgual(marca);
	}
	
	@GetMapping("/com-validade")
	public List<Produto> comValidade(@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataValidade){
		log.info("Listando produtos da validade.");
		return produtoRepository.findComValidade(dataValidade);
	}
	
	@GetMapping("/com-todos-parametros")
    public List<Produto> getWithAllParams(String nome, BigDecimal precoInicial, BigDecimal precoFinal, String marca) {
		log.info("Listando produtos com os filtros {},{},{},{}", nome, precoInicial, precoFinal, marca );
        return produtoRepository.getWithAllParams(nome, precoInicial, precoFinal, marca );
    }
	
}
