package com.example.criteriaespecifications.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.example.criteriaespecifications.domain.model.Produto;

@Repository
public interface ProdutoRepository  
		extends JpaRepository<Produto, Long>, ProdutoRepositoryQueries,
		JpaSpecificationExecutor<Produto>{
	

}
