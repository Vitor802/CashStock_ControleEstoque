package br.com.CashStock_ControleEstoque.repository;

import br.com.CashStock_ControleEstoque.model.Local;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class LocalRepositoryTest {

    @Autowired
    private LocalRepository localRepository;

    private Local local;

    @BeforeEach
    void setUp() {
        // Arrange: Inicializa um objeto Local antes de cada teste
        local = new Local(null, "Armazém Central");
    }

    @Test
    void deveCriarLocal() {
        // Act: Salva o local no banco de dados
        Local savedLocal = localRepository.save(local);

        // Assert: Verifica se o local foi salvo corretamente e tem um ID gerado
        assertNotNull(savedLocal.getId(), "O ID do local não deve ser nulo após o salvamento");
        assertEquals("Armazém Central", savedLocal.getLocalizacao(), "A localização do local deve ser a mesma");
    }

    @Test
    void deveEncontrarLocalPorId() {
        // Arrange: Salva o local no banco de dados
        Local savedLocal = localRepository.save(local);

        // Act: Busca o local pelo ID
        Local foundLocal = localRepository.findById(savedLocal.getId()).orElse(null);

        // Assert: Verifica se o local foi encontrado
        assertNotNull(foundLocal, "Local deve ser encontrado pelo ID");
        assertEquals(savedLocal.getId(), foundLocal.getId(), "O ID deve ser o mesmo");
        assertEquals(savedLocal.getLocalizacao(), foundLocal.getLocalizacao(), "A localização deve ser a mesma");
    }

    @Test
    void deveAtualizarLocal() {
        // Arrange: Salva o local no banco de dados
        Local savedLocal = localRepository.save(local);

        // Atualiza a entidade
        savedLocal.setLocalizacao("Novo Armazém");

        // Act: Atualiza o local no banco de dados
        Local updatedLocal = localRepository.save(savedLocal);

        // Assert: Verifica se as alterações foram feitas corretamente
        assertEquals("Novo Armazém", updatedLocal.getLocalizacao(), "A localização deve ser atualizada");
    }

    @Test
    void deveDeletarLocal() {
        // Arrange: Salva o local no banco de dados
        Local savedLocal = localRepository.save(local);

        // Act: Deleta o local do banco de dados
        localRepository.deleteById(savedLocal.getId());

        // Assert: Verifica se o local foi removido
        assertFalse(localRepository.findById(savedLocal.getId()).isPresent(),
                "Local não deve ser encontrado após a exclusão");
    }
}