package br.eti.inovareti.bluefood.domain.cliente;

import br.eti.inovareti.bluefood.domain.usuario.Usuario;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

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
    //@Pattern(regexp = "[0-9]{11}", message = "O CPF possuí formato inválido.")
    @CPF(message = "O CPF possuí formato inválido.")
    @Column(length = 11, nullable = false)
    private String cpf;

    @NotBlank(message = "O CEP não pode ser vazio.")
    @Pattern(regexp = "[0-9]{8}", message = "O CEP possuí formato inválido.")
    @Column(length = 8)
    private String cep;
}
