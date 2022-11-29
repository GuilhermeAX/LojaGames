package com.example.storegamesonline.repository;

import com.example.storegamesonline.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    public List<Produto> findAllByNomeContainingIgnoreCase(@Param("nome") String nome);
    public List<Produto> findByPrecoGreaterThanOrderByPreco(double preco);//Buscar maior que

    public List<Produto> findByPrecoLessThanOrderByPrecoDesc(double preco);//Buscar menor que
}
