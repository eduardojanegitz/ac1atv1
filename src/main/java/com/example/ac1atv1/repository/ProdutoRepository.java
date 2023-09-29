package com.example.ac1atv1.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.ac1atv1.models.Produto;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
public class ProdutoRepository {
    @Autowired
    private EntityManager entityManager;

    @Transactional
    public Produto inserir(Produto produto) {
        entityManager.merge(produto);
        return produto;
    }

    public List<Produto> obterTodos() {
        return entityManager.createQuery("from Produto",
                Produto.class).getResultList();
    }

    public List<Produto> obterPorNome(String nome) {
        String jpql = " select p from Produto p where p.nome like :nome";
        TypedQuery<Produto> query = entityManager.createQuery(jpql, Produto.class);
        query.setParameter("nome", "%" + nome + "%");
        return query.getResultList();
    }

    public List<Produto> obterPorId(Long id) {
        String jpql = " select p from Produto p where p.id like :id";
        TypedQuery<Produto> query = entityManager.createQuery(jpql, Produto.class);
        query.setParameter("id", id);
        return query.getResultList();
    }

    @Transactional
    public void excluir(Long id) {
        Produto produtoParaExcluir = entityManager.find(Produto.class, id);
        if (produtoParaExcluir != null) {
            entityManager.remove(produtoParaExcluir);
        }

    }

    @Transactional
    public Produto editar(Produto produto) {
        
        return entityManager.merge(produto);
    }

}