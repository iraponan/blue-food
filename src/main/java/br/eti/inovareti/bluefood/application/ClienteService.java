package br.eti.inovareti.bluefood.application;

import br.eti.inovareti.bluefood.domain.cliente.Cliente;
import br.eti.inovareti.bluefood.domain.cliente.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

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
