package br.com.CashStock_ControleEstoque.model;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "enderecoFornecedor")
@Entity(name= "EnderecoFornecedor")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class EnderecoFornecedor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer fkIdFornecedor;
    private String rua;
    private String bairro;
    private String numero;
    private String cidade;
    private String complemento;
    private String cep;
}
