package br.com.CashStock_ControleEstoque.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@Table(name = "historicoCompra")
@Entity(name= "HistoricoCompra")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class HistoricoCompra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer fkProduto;
    private Integer fkIdFornecedor;
    private Date dataCompra;
    private BigDecimal precoUnitario;
    private Double quantidade;
}
