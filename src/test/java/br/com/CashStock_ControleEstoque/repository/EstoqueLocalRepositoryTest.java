package br.com.CashStock_ControleEstoque.repository;

import br.com.CashStock_ControleEstoque.model.EstoqueLocal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class EstoqueLocalRepositoryTest {

    @Autowired
    private EstoqueLocalRepository estoqueLocalRepository;

    private EstoqueLocal estoqueLocal;

    @BeforeEach
    void setUp() {
        // Arrange: Inicializa um objeto EstoqueLocal antes de cada teste
        estoqueLocal = new EstoqueLocal(null, 1, 1, 100.0, new BigDecimal("500.50"));
    }

    @Test
    void deveCriarEstoqueLocal() {
        // Act: Salva o estoqueLocal no banco de dados
        EstoqueLocal savedEstoqueLocal = estoqueLocalRepository.save(estoqueLocal);

        // Assert: Verifica se o estoqueLocal foi salvo corretamente e tem um ID gerado
        assertNotNull(savedEstoqueLocal.getId(), "O ID do estoqueLocal não deve ser nulo após o salvamento");
        assertEquals(100.0, savedEstoqueLocal.getQuantidade(), "A quantidade do estoque local deve ser a mesma");
        assertEquals(new BigDecimal("500.50"), savedEstoqueLocal.getValorEstoque(), "O valor do estoque local deve ser o mesmo");
    }

    @Test
    void deveEncontrarEstoqueLocalPorId() {
        // Arrange: Salva o estoqueLocal no banco de dados
        EstoqueLocal savedEstoqueLocal = estoqueLocalRepository.save(estoqueLocal);

        // Act: Busca o estoqueLocal pelo ID
        EstoqueLocal foundEstoqueLocal = estoqueLocalRepository.findById(savedEstoqueLocal.getId()).orElse(null);

        // Assert: Verifica se o estoqueLocal foi encontrado
        assertNotNull(foundEstoqueLocal, "EstoqueLocal deve ser encontrado pelo ID");
        assertEquals(savedEstoqueLocal.getId(), foundEstoqueLocal.getId(), "O ID deve ser o mesmo");
        assertEquals(savedEstoqueLocal.getQuantidade(), foundEstoqueLocal.getQuantidade(), "A quantidade deve ser a mesma");
    }

    @Test
    void deveAtualizarEstoqueLocal() {
        // Arrange: Salva o estoqueLocal no banco de dados
        EstoqueLocal savedEstoqueLocal = estoqueLocalRepository.save(estoqueLocal);

        // Atualiza a entidade
        savedEstoqueLocal.setQuantidade(200.0);
        savedEstoqueLocal.setValorEstoque(new BigDecimal("750.75"));

        // Act: Atualiza o estoqueLocal no banco de dados
        EstoqueLocal updatedEstoqueLocal = estoqueLocalRepository.save(savedEstoqueLocal);

        // Assert: Verifica se as alterações foram feitas corretamente
        assertEquals(200.0, updatedEstoqueLocal.getQuantidade(), "A quantidade deve ser atualizada");
        assertEquals(new BigDecimal("750.75"), updatedEstoqueLocal.getValorEstoque(), "O valor do estoque local deve ser atualizado");
    }

    @Test
    void deveDeletarEstoqueLocal() {
        // Arrange: Salva o estoqueLocal no banco de dados
        EstoqueLocal savedEstoqueLocal = estoqueLocalRepository.save(estoqueLocal);

        // Act: Deleta o estoqueLocal do banco de dados
        estoqueLocalRepository.deleteById(savedEstoqueLocal.getId());

        // Assert: Verifica se o estoqueLocal foi removido
        assertFalse(estoqueLocalRepository.findById(savedEstoqueLocal.getId()).isPresent(),
                "EstoqueLocal não deve ser encontrado após a exclusão");
    }
}