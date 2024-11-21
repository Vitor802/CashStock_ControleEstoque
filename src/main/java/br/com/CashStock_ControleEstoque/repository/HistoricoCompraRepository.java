package br.com.CashStock_ControleEstoque.repository;

import br.com.CashStock_ControleEstoque.model.HistoricoCompra;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoricoCompraRepository extends JpaRepository<HistoricoCompra,Long> {
}
