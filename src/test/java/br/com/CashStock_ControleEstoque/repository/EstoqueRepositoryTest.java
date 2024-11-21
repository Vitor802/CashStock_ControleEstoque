package br.com.CashStock_ControleEstoque.repository;

import br.com.CashStock_ControleEstoque.model.Estoque;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class EstoqueRepositoryTest {

    @Autowired
    private EstoqueRepository estoqueRepository;

    private Estoque estoque;

    @BeforeEach
    void setUp() {
        // Arrange: Inicializa um objeto Estoque antes de cada teste
        estoque = new Estoque(null, 1, 100.0, new BigDecimal("50.75"));
    }

    @Test
    void deveCriarEstoque() {
        // Act: Salva o estoque no banco de dados
        Estoque savedEstoque = estoqueRepository.save(estoque);

        // Assert: Verifica se o estoque foi salvo corretamente e tem um ID gerado
        assertNotNull(savedEstoque.getId(), "O ID do estoque não deve ser nulo após o salvamento");
        assertEquals(100.0, savedEstoque.getQuantidade(), "A quantidade do estoque deve ser a mesma");
        assertEquals(new BigDecimal("50.75"), savedEstoque.getPrecoCompraMedia(), "O preço de compra médio do estoque deve ser o mesmo");
    }

    @Test
    void deveEncontrarEstoquePorId() {
        // Arrange: Salva o estoque no banco de dados
        Estoque savedEstoque = estoqueRepository.save(estoque);

        // Act: Busca o estoque pelo ID
        Estoque foundEstoque = estoqueRepository.findById(savedEstoque.getId()).orElse(null);

        // Assert: Verifica se o estoque foi encontrado
        assertNotNull(foundEstoque, "Estoque deve ser encontrado pelo ID");
        assertEquals(savedEstoque.getId(), foundEstoque.getId(), "O ID deve ser o mesmo");
        assertEquals(savedEstoque.getQuantidade(), foundEstoque.getQuantidade(), "A quantidade deve ser a mesma");
    }

    @Test
    void deveAtualizarEstoque() {
        // Arrange: Salva o estoque no banco de dados
        Estoque savedEstoque = estoqueRepository.save(estoque);

        // Atualiza a entidade
        savedEstoque.setQuantidade(200.0);
        savedEstoque.setPrecoCompraMedia(new BigDecimal("60.50"));

        // Act: Atualiza o estoque no banco de dados
        Estoque updatedEstoque = estoqueRepository.save(savedEstoque);

        // Assert: Verifica se as alterações foram feitas corretamente
        assertEquals(200.0, updatedEstoque.getQuantidade(), "A quantidade deve ser atualizada");
        assertEquals(new BigDecimal("60.50"), updatedEstoque.getPrecoCompraMedia(), "O preço de compra médio deve ser atualizado");
    }

    @Test
    void deveDeletarEstoque() {
        // Arrange: Salva o estoque no banco de dados
        Estoque savedEstoque = estoqueRepository.save(estoque);

        // Act: Deleta o estoque do banco de dados
        estoqueRepository.deleteById(savedEstoque.getId());

        // Assert: Verifica se o estoque foi removido
        assertFalse(estoqueRepository.findById(savedEstoque.getId()).isPresent(),
                "Estoque não deve ser encontrado após a exclusão");
    }
}