package br.eti.inovareti.bluefood.domain.restaurante;

import br.eti.inovareti.bluefood.domain.usuario.Usuario;
import br.eti.inovareti.bluefood.util.FileType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.br.CNPJ;
import org.springframework.web.multipart.MultipartFile;

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
    //@Pattern(regexp = "[0-9]{14}", message = "O CNPJ possuí formato inválido.")
    @CNPJ(message = "O CNPJ possuí formato inválido.")
    @Column(length = 14, nullable = false)
    private String cnpj;

    @Size(max = 80)
    private String logotipo;

    private transient MultipartFile logotipoFile;

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

    public void setLogotipoFileName() {
        if (this.getId() == null) {
            throw new IllegalStateException("É preciso primeiro gravar o registro.");
        }
        this.logotipo = String.format("%04d-logo.%s", this.getId(), FileType.of(logotipoFile.getContentType()).getExtension());
    }
}
