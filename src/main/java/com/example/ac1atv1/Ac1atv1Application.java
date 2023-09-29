package com.example.ac1atv1;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.ac1atv1.models.CategoriaProduto;
import com.example.ac1atv1.models.Produto;
import com.example.ac1atv1.repository.CategoriaProdutoRepository;
import com.example.ac1atv1.repository.ProdutoRepository;

@SpringBootApplication
public class Ac1atv1Application {
	
	@Bean
	public CommandLineRunner init(@Autowired ProdutoRepository produtoRepository,
	CategoriaProdutoRepository categoriaProdutoRepository) {
		return args -> {
			System.out.println("** INSERIR CATEGORIA **");
			CategoriaProduto c1 = new CategoriaProduto(0, "Tecnologia");
			categoriaProdutoRepository.inserir(c1);

			CategoriaProduto c2 = new CategoriaProduto(0, "Móveis");
			categoriaProdutoRepository.inserir(c2);

			Produto novoProduto = new Produto();
			novoProduto.setNome("Notebook");
			novoProduto.setQuantidade(10);	
			novoProduto.setCategoriaCurso(c1);

			Produto novoProduto2 = new Produto();
			novoProduto2.setNome("Teclado");
			novoProduto2.setQuantidade(10);	
			novoProduto2.setCategoriaCurso(c1);

			produtoRepository.inserir(novoProduto);
			produtoRepository.inserir(novoProduto2);

			System.out.println("** OBTER TODOS **");
			List<Produto> listaProduto = produtoRepository.obterTodos();
			listaProduto.forEach(System.out::println);
			
			System.out.println("** OBTER POR NOME **");
			listaProduto = produtoRepository.obterPorNome("Notebook");
			listaProduto.forEach(System.out::println);

			System.out.println("** OBTER POR ID **");
			listaProduto = produtoRepository.obterPorId((long) 2);
			listaProduto.forEach(System.out::println);
			
			produtoRepository.excluir((long) 1);
			System.out.println("** OBTER TODOS DEPOIS DE EXCLUÍDO **");
			List<Produto> produtosAtualizados = produtoRepository.obterTodos();
			produtosAtualizados.forEach(System.out::println);

			Produto editarProduto = new Produto();
			editarProduto.setId((long) 2);
			editarProduto.setNome("Mesa");
			editarProduto.setQuantidade(5);
			editarProduto.setCategoriaCurso(c2);

			produtoRepository.editar(editarProduto);

			System.out.println("** OBTER TODOS DEPOIS DE EDITADO **");
			List<Produto> produtoEditado = produtoRepository.obterTodos();
			produtoEditado.forEach(System.out::println);

		};
	}

	public static void main(String[] args) {
		SpringApplication.run(Ac1atv1Application.class, args);
	}

}
