package br.com.CashStock_ControleEstoque.repository;

import br.com.CashStock_ControleEstoque.model.Fornecedor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FornecedorRepository extends JpaRepository<Fornecedor, Long> {
}
