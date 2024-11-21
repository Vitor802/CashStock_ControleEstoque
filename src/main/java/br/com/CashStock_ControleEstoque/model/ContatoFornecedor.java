package br.com.CashStock_ControleEstoque.model;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "contatoFornecedor")
@Entity(name= "ContatoFornecedor")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class ContatoFornecedor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer fkIdFornecedor;
    private String nome;
    private String contato;
}
