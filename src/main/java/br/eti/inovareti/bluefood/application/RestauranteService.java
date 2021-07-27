package br.eti.inovareti.bluefood.application;

import br.eti.inovareti.bluefood.domain.cliente.Cliente;
import br.eti.inovareti.bluefood.domain.cliente.ClienteRepository;
import br.eti.inovareti.bluefood.domain.restaurante.Restaurante;
import br.eti.inovareti.bluefood.domain.restaurante.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RestauranteService {

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ImageService imageService;

    @Transactional
    public void saveRestaurante(Restaurante restaurante) throws ValidationException {
        if (!validateEmail(restaurante.getEmail(), restaurante.getId())) {
            throw new ValidationException("O e-mail informado já pertence a outro usuário.");
        }

        if (restaurante.getId() != null) {
            Restaurante restauranteDb = restauranteRepository.findById(restaurante.getId()).orElseThrow();
            restaurante.setSenha(restauranteDb.getSenha());
        } else {
            restaurante.encryptyPassword();
            restaurante = restauranteRepository.save(restaurante);
            restaurante.setLogotipoFileName();
            imageService.uploadLogotipo(restaurante.getLogotipoFile(), restaurante.getLogotipo());
        }
    }

    private boolean validateEmail(String email, Integer id) {
        Cliente cliente = clienteRepository.findByEmail(email);

        if (cliente != null) {
            return false;
        }

        Restaurante restaurante = restauranteRepository.findByEmail(email);
        if (restaurante != null) {
            if (id == null) {
                return false;
            }
            if (!restaurante.getId().equals(id)) {
                return false;
            }
        }
        return true;
    }
}
