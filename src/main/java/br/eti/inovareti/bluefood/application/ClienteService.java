package br.eti.inovareti.bluefood.application;

import br.eti.inovareti.bluefood.domain.cliente.Cliente;
import br.eti.inovareti.bluefood.domain.cliente.ClienteRepository;
import br.eti.inovareti.bluefood.domain.restaurante.Restaurante;
import br.eti.inovareti.bluefood.domain.restaurante.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Transactional
    public void saveCliente(Cliente cliente) throws ValidationException {
        if (!validateEmail(cliente.getEmail(), cliente.getId())) {
            throw new ValidationException("O e-mail informado já pertence a outro usuário.");
        }
        if (cliente.getId() != null) {
            Cliente clienteDb = clienteRepository.findById(cliente.getId()).orElseThrow();
            cliente.setSenha(cliente.getSenha());
        } else {
            cliente.encryptyPassword();
        }
        clienteRepository.save(cliente);
    }

    private boolean validateEmail(String email, Integer id) {
        Restaurante restaurante = restauranteRepository.findByEmail(email);

        if (restaurante != null) {
            return false;
        }

        Cliente cliente = clienteRepository.findByEmail(email);
        if (cliente != null) {
            if (id == null) {
                return false;
            }
            if (!cliente.getId().equals(id)) {
                return false;
            }
        }
        return true;
    }
}
