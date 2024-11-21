package br.com.CashStock_ControleEstoque.repository;

import br.com.CashStock_ControleEstoque.model.HistoricoCompra;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class HistoricoCompraRepositoryTest {

    @Autowired
    private HistoricoCompraRepository historicoCompraRepository;

    private HistoricoCompra historicoCompra;

    @BeforeEach
    void setUp() {
        // Arrange: Inicializa um objeto HistoricoCompra antes de cada teste
        historicoCompra = new HistoricoCompra(null, 1, 1, new Date(), new BigDecimal("10.50"), 100.0);
    }

    @Test
    void deveCriarHistoricoCompra() {
        // Act: Salva o historicoCompra no banco de dados
        HistoricoCompra savedHistoricoCompra = historicoCompraRepository.save(historicoCompra);

        // Assert: Verifica se o historicoCompra foi salvo corretamente e tem um ID gerado
        assertNotNull(savedHistoricoCompra.getId(), "O ID do historicoCompra não deve ser nulo após o salvamento");
        assertEquals(1, savedHistoricoCompra.getFkProduto(), "O ID do produto deve ser o mesmo");
        assertEquals(1, savedHistoricoCompra.getFkIdFornecedor(), "O ID do fornecedor deve ser o mesmo");
        assertEquals(new BigDecimal("10.50"), savedHistoricoCompra.getPrecoUnitario(), "O preço unitário deve ser o mesmo");
        assertEquals(100.0, savedHistoricoCompra.getQuantidade(), 0.0, "A quantidade deve ser a mesma");
    }

    @Test
    void deveEncontrarHistoricoCompraPorId() {
        // Arrange: Salva o historicoCompra no banco de dados
        HistoricoCompra savedHistoricoCompra = historicoCompraRepository.save(historicoCompra);

        // Act: Busca o historicoCompra pelo ID
        HistoricoCompra foundHistoricoCompra = historicoCompraRepository.findById(savedHistoricoCompra.getId()).orElse(null);

        // Assert: Verifica se o historicoCompra foi encontrado
        assertNotNull(foundHistoricoCompra, "HistoricoCompra deve ser encontrado pelo ID");
        assertEquals(savedHistoricoCompra.getId(), foundHistoricoCompra.getId(), "O ID deve ser o mesmo");
        assertEquals(savedHistoricoCompra.getFkProduto(), foundHistoricoCompra.getFkProduto(), "O ID do produto deve ser o mesmo");
    }

    @Test
    void deveAtualizarHistoricoCompra() {
        // Arrange: Salva o historicoCompra no banco de dados
        HistoricoCompra savedHistoricoCompra = historicoCompraRepository.save(historicoCompra);

        // Atualiza a entidade
        savedHistoricoCompra.setPrecoUnitario(new BigDecimal("12.75"));
        savedHistoricoCompra.setQuantidade(150.0);

        // Act: Atualiza o historicoCompra no banco de dados
        HistoricoCompra updatedHistoricoCompra = historicoCompraRepository.save(savedHistoricoCompra);

        // Assert: Verifica se as alterações foram feitas corretamente
        assertEquals(new BigDecimal("12.75"), updatedHistoricoCompra.getPrecoUnitario(), "O preço unitário deve ser atualizado");
        assertEquals(150.0, updatedHistoricoCompra.getQuantidade(), 0.0, "A quantidade deve ser atualizada");
    }

    @Test
    void deveDeletarHistoricoCompra() {
        // Arrange: Salva o historicoCompra no banco de dados
        HistoricoCompra savedHistoricoCompra = historicoCompraRepository.save(historicoCompra);

        // Act: Deleta o historicoCompra do banco de dados
        historicoCompraRepository.deleteById(savedHistoricoCompra.getId());

        // Assert: Verifica se o historicoCompra foi removido
        assertFalse(historicoCompraRepository.findById(savedHistoricoCompra.getId()).isPresent(),
                "HistoricoCompra não deve ser encontrado após a exclusão");
    }
}