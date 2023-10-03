package com.example.criteriaespecifications.controller;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.standaloneSetup;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;

import com.example.criteriaespecifications.api.controller.ProdutoController;
import com.example.criteriaespecifications.domain.model.Produto;
import com.example.criteriaespecifications.domain.repository.ProdutoRepository;

import io.restassured.http.ContentType;

@WebMvcTest
public class ProdutoControllerTest {
	
	@Autowired
	ProdutoController produtoController;
	
	@MockBean
	ProdutoRepository produtoRepository;
	
	@BeforeEach
	public void setup() {
		standaloneSetup(this.produtoController);
	}
	
	@Test
	public void deveRetornarSucesso_QuandoBuscarComTodosParamettos() {
	
	when(this.produtoRepository.getWithAllParams("a", null, null, null))
		.thenReturn(
				(List<Produto>) new Produto(1L, "null", "UN", null, 10, "Nike", new BigDecimal(1000.0)));
		
	given()
		.accept(ContentType.JSON)
	.when()
		.get("/com-todos-parametros", "a")
	.then()
		.statusCode(HttpStatus.OK.value());
		
	}
	
}

 