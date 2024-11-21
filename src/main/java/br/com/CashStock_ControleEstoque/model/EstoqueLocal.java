package br.com.CashStock_ControleEstoque.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Table(name = "estoqueLocal")
@Entity(name= "EstoqueLocal")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class EstoqueLocal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer fkIdEstoque;
    private Integer fkIdLocal;
    private Double quantidade;
    private BigDecimal valorEstoque;
}
