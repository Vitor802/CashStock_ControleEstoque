package br.com.CashStock_ControleEstoque.repository;

import br.com.CashStock_ControleEstoque.model.ProdutoFinanceiro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoFinanceiroRepository extends JpaRepository<ProdutoFinanceiro,Long> {
}
