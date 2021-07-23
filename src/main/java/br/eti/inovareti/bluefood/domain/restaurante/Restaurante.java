package br.eti.inovareti.bluefood.domain.restaurante;

import br.eti.inovareti.bluefood.domain.usuario.Usuario;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
@Entity
public class Restaurante extends Usuario {

    @NotBlank(message = "O CNPJ não pode ser vazio.")
    @Pattern(regexp = "[0-9]{14}", message = "O CNPJ possuí formato inválido.")
    //@CNPJ
    @Column(length = 14, nullable = false)
    private String cnpj;

    @Size(max = 80)
    private String logotipo;

    @NotNull(message = "A taxa de entrega não pode ser vazia.")
    @Min(0)
    @Max(99)
    private BigDecimal taxaEntrega;

    @NotNull(message = "O tempo de entrega não pode ser vazio.")
    @Min(0)
    @Max(120)
    private Integer tempoEntregaBase;

    @ManyToMany
    @JoinTable(name = "restaurante_has_categoria", joinColumns = @JoinColumn(name = "restaurante_id"), inverseJoinColumns = @JoinColumn(name = "categoria_restaurante_id"))
    @Size(min = 1, message = "O restaurante precisa ter pelo menos uma categoria.")
    @ToString.Exclude
    private Set<CategoriaRestaurante> categorias = new HashSet<>(0);
}
