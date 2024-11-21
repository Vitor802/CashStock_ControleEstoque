package br.com.CashStock_ControleEstoque.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Table(name = "estoque")
@Entity(name= "Estoque")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")

public class Estoque {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer fkProduto;
    private Double quantidade;
    private BigDecimal precoCompraMedia;
}
