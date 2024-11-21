package br.com.CashStock_ControleEstoque.repository;

import br.com.CashStock_ControleEstoque.model.EstoqueLocal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstoqueLocalRepository extends JpaRepository<EstoqueLocal,Long> {
}
