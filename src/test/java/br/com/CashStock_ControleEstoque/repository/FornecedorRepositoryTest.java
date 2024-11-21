package br.com.CashStock_ControleEstoque.repository;

import br.com.CashStock_ControleEstoque.model.Fornecedor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class FornecedorRepositoryTest {

    @Autowired
    private FornecedorRepository fornecedorRepository;

    private Fornecedor fornecedor;

    @BeforeEach
    void setUp() {
        // Arrange: Inicializa um objeto Fornecedor antes de cada teste
        fornecedor = new Fornecedor(null, "Razão Social A", "Nome Fantasia A", "12345678000199", "123456789", "987654321");
    }

    @Test
    void deveCriarFornecedor() {
        // Act: Salva o fornecedor no banco de dados
        Fornecedor savedFornecedor = fornecedorRepository.save(fornecedor);

        // Assert: Verifica se o fornecedor foi salvo corretamente e tem um ID gerado
        assertNotNull(savedFornecedor.getId(), "O ID do fornecedor não deve ser nulo após o salvamento");
        assertEquals("Razão Social A", savedFornecedor.getRazaoSocial(), "A razão social do fornecedor deve ser a mesma");
        assertEquals("Nome Fantasia A", savedFornecedor.getNomeFantasia(), "O nome fantasia do fornecedor deve ser o mesmo");
    }

    @Test
    void deveEncontrarFornecedorPorId() {
        // Arrange: Salva o fornecedor no banco de dados
        Fornecedor savedFornecedor = fornecedorRepository.save(fornecedor);

        // Act: Busca o fornecedor pelo ID
        Fornecedor foundFornecedor = fornecedorRepository.findById(savedFornecedor.getId()).orElse(null);

        // Assert: Verifica se o fornecedor foi encontrado
        assertNotNull(foundFornecedor, "Fornecedor deve ser encontrado pelo ID");
        assertEquals(savedFornecedor.getId(), foundFornecedor.getId(), "O ID deve ser o mesmo");
        assertEquals(savedFornecedor.getRazaoSocial(), foundFornecedor.getRazaoSocial(), "A razão social deve ser a mesma");
    }

    @Test
    void deveAtualizarFornecedor() {
        // Arrange: Salva o fornecedor no banco de dados
        Fornecedor savedFornecedor = fornecedorRepository.save(fornecedor);

        // Atualiza a entidade
        savedFornecedor.setRazaoSocial("Razão Social Atualizada");
        savedFornecedor.setNomeFantasia("Nome Fantasia Atualizado");

        // Act: Atualiza o fornecedor no banco de dados
        Fornecedor updatedFornecedor = fornecedorRepository.save(savedFornecedor);

        // Assert: Verifica se as alterações foram feitas corretamente
        assertEquals("Razão Social Atualizada", updatedFornecedor.getRazaoSocial(), "A razão social deve ser atualizada");
        assertEquals("Nome Fantasia Atualizado", updatedFornecedor.getNomeFantasia(), "O nome fantasia deve ser atualizado");
    }

    @Test
    void deveDeletarFornecedor() {
        // Arrange: Salva o fornecedor no banco de dados
        Fornecedor savedFornecedor = fornecedorRepository.save(fornecedor);

        // Act: Deleta o fornecedor do banco de dados
        fornecedorRepository.deleteById(savedFornecedor.getId());

        // Assert: Verifica se o fornecedor foi removido
        assertFalse(fornecedorRepository.findById(savedFornecedor.getId()).isPresent(),
                "Fornecedor não deve ser encontrado após a exclusão");
    }
}