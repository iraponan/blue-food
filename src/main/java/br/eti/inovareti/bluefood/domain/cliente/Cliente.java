package br.eti.inovareti.bluefood.domain.cliente;

import br.eti.inovareti.bluefood.domain.usuario.Usuario;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
@Entity
public class Cliente extends Usuario {

    @NotBlank(message = "O CPF não pode ser vazio.")
    @Pattern(regexp = "[0-1] {11}", message = "O CPF possuí formato inválido.")
    //@CPF
    @Column(length = 11, nullable = false)
    private String cpf;

    @NotBlank(message = "O CEP não pode ser vazio.")
    @Pattern(regexp = "[0-1] {8}", message = "O CEP possuí formato inválido.")
    @Column(length = 8)
    private String cep;
}
