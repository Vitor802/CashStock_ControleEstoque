package br.com.CashStock_ControleEstoque.repository;

import br.com.CashStock_ControleEstoque.model.ContatoFornecedor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class ContatoFornecedorRepositoryTest {

    @Autowired
    private ContatoFornecedorRepository contatoFornecedorRepository;

    private ContatoFornecedor contatoFornecedor;

    @BeforeEach
    void setUp() {
        // Arrange: Inicializa um objeto ContatoFornecedor antes de cada teste
        contatoFornecedor = new ContatoFornecedor(null, 1, "Fornecedor A", "123456789");
    }

    @Test
    void deveCriarContatoFornecedor() {
        // Act: Salva o contatoFornecedor no banco de dados
        ContatoFornecedor savedContatoFornecedor = contatoFornecedorRepository.save(contatoFornecedor);

        // Assert: Verifica se o contatoFornecedor foi salvo corretamente e tem um ID gerado
        assertNotNull(savedContatoFornecedor.getId(), "O ID do contatoFornecedor não deve ser nulo após o salvamento");
        assertEquals("Fornecedor A", savedContatoFornecedor.getNome(), "O nome do contatoFornecedor deve ser o mesmo");
        assertEquals("123456789", savedContatoFornecedor.getContato(), "O contato do fornecedor deve ser o mesmo");
    }

    @Test
    void deveEncontrarContatoFornecedorPorId() {
        // Arrange: Salva o contatoFornecedor no banco de dados
        ContatoFornecedor savedContatoFornecedor = contatoFornecedorRepository.save(contatoFornecedor);

        // Act: Busca o contatoFornecedor pelo ID
        ContatoFornecedor foundContatoFornecedor = contatoFornecedorRepository.findById(savedContatoFornecedor.getId()).orElse(null);

        // Assert: Verifica se o contatoFornecedor foi encontrado
        assertNotNull(foundContatoFornecedor, "ContatoFornecedor deve ser encontrado pelo ID");
        assertEquals(savedContatoFornecedor.getId(), foundContatoFornecedor.getId(), "O ID deve ser o mesmo");
        assertEquals(savedContatoFornecedor.getNome(), foundContatoFornecedor.getNome(), "O nome deve ser o mesmo");
    }

    @Test
    void deveAtualizarContatoFornecedor() {
        // Arrange: Salva o contatoFornecedor no banco de dados
        ContatoFornecedor savedContatoFornecedor = contatoFornecedorRepository.save(contatoFornecedor);

        // Atualiza a entidade
        savedContatoFornecedor.setNome("Fornecedor Atualizado");
        savedContatoFornecedor.setContato("987654321");

        // Act: Atualiza o contatoFornecedor no banco de dados
        ContatoFornecedor updatedContatoFornecedor = contatoFornecedorRepository.save(savedContatoFornecedor);

        // Assert: Verifica se as alterações foram feitas corretamente
        assertEquals("Fornecedor Atualizado", updatedContatoFornecedor.getNome(), "O nome deve ser atualizado");
        assertEquals("987654321", updatedContatoFornecedor.getContato(), "O contato deve ser atualizado");
    }

    @Test
    void deveDeletarContatoFornecedor() {
        // Arrange: Salva o contatoFornecedor no banco de dados
        ContatoFornecedor savedContatoFornecedor = contatoFornecedorRepository.save(contatoFornecedor);

        // Act: Deleta o contatoFornecedor do banco de dados
        contatoFornecedorRepository.deleteById(savedContatoFornecedor.getId());

        // Assert: Verifica se o contatoFornecedor foi removido
        assertFalse(contatoFornecedorRepository.findById(savedContatoFornecedor.getId()).isPresent(),
                "ContatoFornecedor não deve ser encontrado após a exclusão");
    }
}