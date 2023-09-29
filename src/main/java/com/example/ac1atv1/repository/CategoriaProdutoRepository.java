package com.example.ac1atv1.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.ac1atv1.models.CategoriaProduto;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
public class CategoriaProdutoRepository {
    @Autowired
    private EntityManager entityManager;

    @Transactional
    public CategoriaProduto inserir(CategoriaProduto categoriaProduto) {
        entityManager.persist(categoriaProduto);
        return categoriaProduto;
    }

    public List<CategoriaProduto> obterTodos() {
        return entityManager
                .createQuery("from CategoriaProduto",
                        CategoriaProduto.class)
                .getResultList();
    }

    public List<CategoriaProduto> obterPorId(int id) {
        String jpql = " select c from CategoriaProduto c where c.id like :id";
        TypedQuery<CategoriaProduto> query = entityManager.createQuery(jpql, CategoriaProduto.class);
        query.setParameter("id", id);
        return query.getResultList();
    }

}