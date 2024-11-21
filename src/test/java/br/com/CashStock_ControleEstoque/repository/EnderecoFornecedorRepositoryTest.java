package br.com.CashStock_ControleEstoque.repository;

import br.com.CashStock_ControleEstoque.model.EnderecoFornecedor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class EnderecoFornecedorRepositoryTest {

    @Autowired
    private EnderecoFornecedorRepository enderecoFornecedorRepository;

    private EnderecoFornecedor enderecoFornecedor;

    @BeforeEach
    void setUp() {
        // Arrange: Inicializa um objeto EnderecoFornecedor antes de cada teste
        enderecoFornecedor = new EnderecoFornecedor(null, 1, "Rua Teste", "Bairro Teste", "123", "Cidade Teste", "Complemento Teste", "12345-678");
    }

    @Test
    void deveCriarEnderecoFornecedor() {
        // Act: Salva o enderecoFornecedor no banco de dados
        EnderecoFornecedor savedEnderecoFornecedor = enderecoFornecedorRepository.save(enderecoFornecedor);

        // Assert: Verifica se o enderecoFornecedor foi salvo corretamente e tem um ID gerado
        assertNotNull(savedEnderecoFornecedor.getId(), "O ID do enderecoFornecedor não deve ser nulo após o salvamento");
        assertEquals("Rua Teste", savedEnderecoFornecedor.getRua(), "A rua do enderecoFornecedor deve ser a mesma");
        assertEquals("12345-678", savedEnderecoFornecedor.getCep(), "O CEP do enderecoFornecedor deve ser o mesmo");
    }

    @Test
    void deveEncontrarEnderecoFornecedorPorId() {
        // Arrange: Salva o enderecoFornecedor no banco de dados
        EnderecoFornecedor savedEnderecoFornecedor = enderecoFornecedorRepository.save(enderecoFornecedor);

        // Act: Busca o enderecoFornecedor pelo ID
        EnderecoFornecedor foundEnderecoFornecedor = enderecoFornecedorRepository.findById(savedEnderecoFornecedor.getId()).orElse(null);

        // Assert: Verifica se o enderecoFornecedor foi encontrado
        assertNotNull(foundEnderecoFornecedor, "EnderecoFornecedor deve ser encontrado pelo ID");
        assertEquals(savedEnderecoFornecedor.getId(), foundEnderecoFornecedor.getId(), "O ID deve ser o mesmo");
        assertEquals(savedEnderecoFornecedor.getRua(), foundEnderecoFornecedor.getRua(), "A rua deve ser a mesma");
    }

    @Test
    void deveAtualizarEnderecoFornecedor() {
        // Arrange: Salva o enderecoFornecedor no banco de dados
        EnderecoFornecedor savedEnderecoFornecedor = enderecoFornecedorRepository.save(enderecoFornecedor);

        // Atualiza a entidade
        savedEnderecoFornecedor.setRua("Rua Atualizada");
        savedEnderecoFornecedor.setCep("98765-432");

        // Act: Atualiza o enderecoFornecedor no banco de dados
        EnderecoFornecedor updatedEnderecoFornecedor = enderecoFornecedorRepository.save(savedEnderecoFornecedor);

        // Assert: Verifica se as alterações foram feitas corretamente
        assertEquals("Rua Atualizada", updatedEnderecoFornecedor.getRua(), "A rua deve ser atualizada");
        assertEquals("98765-432", updatedEnderecoFornecedor.getCep(), "O CEP deve ser atualizado");
    }

    @Test
    void deveDeletarEnderecoFornecedor() {
        // Arrange: Salva o enderecoFornecedor no banco de dados
        EnderecoFornecedor savedEnderecoFornecedor = enderecoFornecedorRepository.save(enderecoFornecedor);

        // Act: Deleta o enderecoFornecedor do banco de dados
        enderecoFornecedorRepository.deleteById(savedEnderecoFornecedor.getId());

        // Assert: Verifica se o enderecoFornecedor foi removido
        assertFalse(enderecoFornecedorRepository.findById(savedEnderecoFornecedor.getId()).isPresent(),
                "EnderecoFornecedor não deve ser encontrado após a exclusão");
    }
}