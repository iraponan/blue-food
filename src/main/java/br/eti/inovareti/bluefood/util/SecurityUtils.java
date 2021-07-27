package br.eti.inovareti.bluefood.util;

import br.eti.inovareti.bluefood.domain.cliente.Cliente;
import br.eti.inovareti.bluefood.domain.restaurante.Restaurante;
import br.eti.inovareti.bluefood.infrastructure.web.security.LoggedUser;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtils {

    public static LoggedUser loggedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof AnonymousAuthenticationToken) {
            return null;
        }
        return (LoggedUser) authentication.getPrincipal();
    }

    public static Cliente loggedCliente() {
        LoggedUser loggedUser = loggedUser();
        if (loggedUser == null) {
            throw  new IllegalStateException("Não existe um usuário logado.");
        }
        if (!(loggedUser.getUsuario() instanceof Cliente)) {
            throw new IllegalStateException("O usuário logado não é umn cliente.");
        }
        return (Cliente) loggedUser.getUsuario();
    }

    public static Restaurante loggedrestaurante() {
        LoggedUser loggedUser = loggedUser();
        if (loggedUser == null) {
            throw  new IllegalStateException("Não existe um usuário logado.");
        }
        if (!(loggedUser.getUsuario() instanceof Restaurante)) {
            throw new IllegalStateException("O usuário logado não é umn cliente.");
        }
        return (Restaurante) loggedUser.getUsuario();
    }
}
