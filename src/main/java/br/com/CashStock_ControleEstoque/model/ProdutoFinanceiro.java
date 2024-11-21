package br.com.CashStock_ControleEstoque.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Table(name = "produtoFinanceiro")
@Entity(name = "ProdutoFinanceiro")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class ProdutoFinanceiro {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private Integer fkIdProduto;
        private BigDecimal precoVenda;
        private Double margemLucro;
}
