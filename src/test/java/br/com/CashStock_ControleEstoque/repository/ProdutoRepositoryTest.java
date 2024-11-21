package br.com.CashStock_ControleEstoque.repository;

import br.com.CashStock_ControleEstoque.model.Produto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class ProdutoRepositoryTest {
    @Autowired
    private ProdutoRepository produtoRepository;

    private Produto produto;

    @BeforeEach
    void setUp() {
        // Arrange: Inicializa um objeto Produto antes de cada teste
        produto = new Produto(null, "Produto A", "123456789");
    }

    @Test
    void deveCriarProduto() {
        // Act: Salva o produto no banco de dados
        Produto savedProduto = produtoRepository.save(produto);

        // Assert: Verifica se o produto foi salvo corretamente e tem um ID gerado
        assertNotNull(savedProduto.getId(), "O ID do produto não deve ser nulo após o salvamento");
        assertEquals("Produto A", savedProduto.getDescricao(), "A descrição do produto deve ser a mesma");
        assertEquals("123456789", savedProduto.getCodigoBarra(), "O código de barra do produto deve ser o mesmo");
    }

    @Test
    void deveEncontrarProdutoPorId() {
        // Arrange: Salva o produto no banco de dados
        Produto savedProduto = produtoRepository.save(produto);

        // Act: Busca o produto pelo ID
        Produto foundProduto = produtoRepository.findById(savedProduto.getId()).orElse(null);

        // Assert: Verifica se o produto foi encontrado
        assertNotNull(foundProduto, "Produto deve ser encontrado pelo ID");
        assertEquals(savedProduto.getId(), foundProduto.getId(), "O ID deve ser o mesmo");
        assertEquals(savedProduto.getDescricao(), foundProduto.getDescricao(), "A descrição deve ser a mesma");
    }

    @Test
    void deveAtualizarProduto() {
        // Arrange: Salva o produto no banco de dados
        Produto savedProduto = produtoRepository.save(produto);

        // Atualiza a entidade
        savedProduto.setDescricao("Produto Atualizado");
        savedProduto.setCodigoBarra("987654321");

        // Act: Atualiza o produto no banco de dados
        Produto updatedProduto = produtoRepository.save(savedProduto);

        // Assert: Verifica se as alterações foram feitas corretamente
        assertEquals("Produto Atualizado", updatedProduto.getDescricao(), "A descrição deve ser atualizada");
        assertEquals("987654321", updatedProduto.getCodigoBarra(), "O código de barra deve ser atualizado");
    }

    @Test
    void deveDeletarProduto() {
        // Arrange: Salva o produto no banco de dados
        Produto savedProduto = produtoRepository.save(produto);

        // Act: Deleta o produto do banco de dados
        produtoRepository.deleteById(savedProduto.getId());

        // Assert: Verifica se o produto foi removido
        assertFalse(produtoRepository.findById(savedProduto.getId()).isPresent(),
                "Produto não deve ser encontrado após a exclusão");
    }
}