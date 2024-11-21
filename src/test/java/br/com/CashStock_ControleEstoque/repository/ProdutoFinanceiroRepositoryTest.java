package br.com.CashStock_ControleEstoque.repository;

import br.com.CashStock_ControleEstoque.model.ProdutoFinanceiro;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class ProdutoFinanceiroRepositoryTest {

    @Autowired
    private ProdutoFinanceiroRepository produtoFinanceiroRepository;

    private ProdutoFinanceiro produtoFinanceiro;

    @BeforeEach
    void setUp() {
        // Arrange: Inicializa um objeto ProdutoFinanceiro antes de cada teste
        produtoFinanceiro = new ProdutoFinanceiro(null, 1, BigDecimal.valueOf(200.50), 20.0);
    }

    @Test
    void deveCriarProdutoFinanceiro() {
        // Act: Salva o produtoFinanceiro no banco de dados
        ProdutoFinanceiro savedProdutoFinanceiro = produtoFinanceiroRepository.save(produtoFinanceiro);

        // Assert: Verifica se o produtoFinanceiro foi salvo corretamente e tem um ID gerado
        assertNotNull(savedProdutoFinanceiro.getId(), "O ID do ProdutoFinanceiro não deve ser nulo após o salvamento");
        assertEquals(BigDecimal.valueOf(200.50), savedProdutoFinanceiro.getPrecoVenda(), "O preço de venda deve ser o mesmo");
        assertEquals(20.0, savedProdutoFinanceiro.getMargemLucro(), "A margem de lucro deve ser a mesma");
    }

    @Test
    void deveEncontrarProdutoFinanceiroPorId() {
        // Arrange: Salva o produtoFinanceiro no banco de dados
        ProdutoFinanceiro savedProdutoFinanceiro = produtoFinanceiroRepository.save(produtoFinanceiro);

        // Act: Busca o produtoFinanceiro pelo ID
        ProdutoFinanceiro foundProdutoFinanceiro = produtoFinanceiroRepository.findById(savedProdutoFinanceiro.getId()).orElse(null);

        // Assert: Verifica se o produtoFinanceiro foi encontrado
        assertNotNull(foundProdutoFinanceiro, "ProdutoFinanceiro deve ser encontrado pelo ID");
        assertEquals(savedProdutoFinanceiro.getId(), foundProdutoFinanceiro.getId(), "O ID deve ser o mesmo");
        assertEquals(savedProdutoFinanceiro.getPrecoVenda(), foundProdutoFinanceiro.getPrecoVenda(), "O preço de venda deve ser o mesmo");
        assertEquals(savedProdutoFinanceiro.getMargemLucro(), foundProdutoFinanceiro.getMargemLucro(), "A margem de lucro deve ser a mesma");
    }

    @Test
    void deveAtualizarProdutoFinanceiro() {
        // Arrange: Salva o produtoFinanceiro no banco de dados
        ProdutoFinanceiro savedProdutoFinanceiro = produtoFinanceiroRepository.save(produtoFinanceiro);

        // Atualiza a entidade
        savedProdutoFinanceiro.setPrecoVenda(BigDecimal.valueOf(250.75));
        savedProdutoFinanceiro.setMargemLucro(25.0);

        // Act: Atualiza o produtoFinanceiro no banco de dados
        ProdutoFinanceiro updatedProdutoFinanceiro = produtoFinanceiroRepository.save(savedProdutoFinanceiro);

        // Assert: Verifica se as alterações foram feitas corretamente
        assertEquals(BigDecimal.valueOf(250.75), updatedProdutoFinanceiro.getPrecoVenda(), "O preço de venda deve ser atualizado");
        assertEquals(25.0, updatedProdutoFinanceiro.getMargemLucro(), "A margem de lucro deve ser atualizada");
    }

    @Test
    void deveDeletarProdutoFinanceiro() {
        // Arrange: Salva o produtoFinanceiro no banco de dados
        ProdutoFinanceiro savedProdutoFinanceiro = produtoFinanceiroRepository.save(produtoFinanceiro);

        // Act: Deleta o produtoFinanceiro do banco de dados
        produtoFinanceiroRepository.deleteById(savedProdutoFinanceiro.getId());

        // Assert: Verifica se o produtoFinanceiro foi removido
        assertFalse(produtoFinanceiroRepository.findById(savedProdutoFinanceiro.getId()).isPresent(),
                "ProdutoFinanceiro não deve ser encontrado após a exclusão");
    }
}