package br.com.CashStock_ControleEstoque.repository;

import br.com.CashStock_ControleEstoque.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto,Long> {
}
