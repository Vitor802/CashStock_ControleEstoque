package br.com.CashStock_ControleEstoque.model;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "local")
@Entity(name= "Local")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Local {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String localizacao;
}
