package br.com.CashStock_ControleEstoque.repository;

import br.com.CashStock_ControleEstoque.model.Estoque;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstoqueRepository extends JpaRepository<Estoque, Long> {
}
