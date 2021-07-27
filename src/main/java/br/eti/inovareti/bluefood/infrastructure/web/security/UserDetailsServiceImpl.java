package br.eti.inovareti.bluefood.infrastructure.web.security;

import br.eti.inovareti.bluefood.domain.cliente.Cliente;
import br.eti.inovareti.bluefood.domain.cliente.ClienteRepository;
import br.eti.inovareti.bluefood.domain.restaurante.RestauranteRepository;
import br.eti.inovareti.bluefood.domain.usuario.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = clienteRepository.findByEmail(username);
        if (usuario == null) {
            usuario = restauranteRepository.findByEmail(username);
            if (usuario == null) {
                throw new UsernameNotFoundException(username);
            }
        }
        return new LoggedUser(usuario);
    }
}
