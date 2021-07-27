package br.eti.inovareti.bluefood.application.test;

import br.eti.inovareti.bluefood.domain.cliente.Cliente;
import br.eti.inovareti.bluefood.domain.cliente.ClienteRepository;
import br.eti.inovareti.bluefood.domain.restaurante.CategoriaRestaurante;
import br.eti.inovareti.bluefood.domain.restaurante.CategoriaRestauranteRepository;
import br.eti.inovareti.bluefood.domain.restaurante.Restaurante;
import br.eti.inovareti.bluefood.domain.restaurante.RestauranteRepository;
import br.eti.inovareti.bluefood.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class InsertDataForTesting {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private CategoriaRestauranteRepository categoriaRestauranteRepository;

    @EventListener
    public void onApplicationEvent(ContextRefreshedEvent event) {
        Cliente[] clientes =  clientes();
        Restaurante[] restaurantes =  restaurantes();
    }

    private Cliente[] clientes() {
        List<Cliente> clientes = new ArrayList<>();

        Cliente c = new Cliente();
        c.setNome("João Silva");
        c.setEmail("joao@inovareti.eti.br");
        c.setSenha(StringUtils.encrypt("joao"));
        c.setCep("89300100");
        c.setCpf("50292518048");
        c.setTelefone("99355430001");
        clienteRepository.save(c);
        clientes.add(c);

        c = new Cliente();
        c.setNome("Maria Torres");
        c.setEmail("maria@inovareti.eti.br");
        c.setSenha(StringUtils.encrypt("maria"));
        c.setCep("89300101");
        c.setCpf("59212657052");
        c.setTelefone("99355430002");
        clienteRepository.save(c);
        clientes.add(c);

        return clientes.toArray(new Cliente[clientes.size()]);
    }

    private Restaurante[] restaurantes() {
        List<Restaurante> restaurantes = new ArrayList<>();

        CategoriaRestaurante categoriaPizza = categoriaRestauranteRepository.findById(1).orElseThrow();
        CategoriaRestaurante categoriaSanduiche = categoriaRestauranteRepository.findById(2).orElseThrow();
        CategoriaRestaurante categoriaSobremesa = categoriaRestauranteRepository.findById(5).orElseThrow();
        CategoriaRestaurante categoriaJapones = categoriaRestauranteRepository.findById(6).orElseThrow();

        Restaurante r = new Restaurante();
        r.setNome("Bubger King");
        r.setEmail("burger.king@inovareti.eti.br");
        r.setSenha(StringUtils.encrypt("burger.king"));
        r.setCnpj("79783367000189");
        r.setTaxaEntrega(BigDecimal.valueOf(3.2));
        r.setTelefone("99876671010");
        r.getCategorias().add(categoriaSanduiche);
        r.getCategorias().add(categoriaSobremesa);
        r.setLogotipo("0001-logo.png");
        r.setTempoEntregaBase(30);
        restauranteRepository.save(r);
        restaurantes.add(r);

        r = new Restaurante();
        r.setNome("Mc Naldo´s");
        r.setEmail("mc.naldos@inovareti.eti.br");
        r.setSenha(StringUtils.encrypt("mc.naldos"));
        r.setCnpj("61914179000187");
        r.setTaxaEntrega(BigDecimal.valueOf(4.5));
        r.setTelefone("99876671011");
        r.getCategorias().add(categoriaSanduiche);
        r.getCategorias().add(categoriaSobremesa);
        r.setLogotipo("0002-logo.png");
        r.setTempoEntregaBase(25);
        restauranteRepository.save(r);
        restaurantes.add(r);

        r = new Restaurante();
        r.setNome("Sbubby");
        r.setEmail("sbubby@inovareti.eti.br");
        r.setSenha(StringUtils.encrypt("sbubby"));
        r.setCnpj("79497662000179");
        r.setTaxaEntrega(BigDecimal.valueOf(12.2));
        r.setTelefone("99876671012");
        r.getCategorias().add(categoriaSanduiche);
        r.getCategorias().add(categoriaSobremesa);
        r.setLogotipo("0003-logo.png");
        r.setTempoEntregaBase(38);
        restauranteRepository.save(r);
        restaurantes.add(r);

        r = new Restaurante();
        r.setNome("Pizza Brut");
        r.setEmail("pizza.brut@inovareti.eti.br");
        r.setSenha(StringUtils.encrypt("pizza.brut"));
        r.setCnpj("69049663000105");
        r.setTaxaEntrega(BigDecimal.valueOf(9.8));
        r.setTelefone("99876671013");
        r.getCategorias().add(categoriaPizza);
        r.getCategorias().add(categoriaSobremesa);
        r.setLogotipo("0004-logo.png");
        r.setTempoEntregaBase(22);
        restauranteRepository.save(r);
        restaurantes.add(r);

        r = new Restaurante();
        r.setNome("Wiki Japa");
        r.setEmail("wiki.japa@inovareti.eti.br");
        r.setSenha(StringUtils.encrypt("wiki.japa"));
        r.setCnpj("23816469000107");
        r.setTaxaEntrega(BigDecimal.valueOf(14.9));
        r.setTelefone("99876671014");
        r.getCategorias().add(categoriaJapones);
        r.getCategorias().add(categoriaSobremesa);
        r.setLogotipo("0005-logo.png");
        r.setTempoEntregaBase(19);
        restauranteRepository.save(r);
        restaurantes.add(r);

        return restaurantes.toArray(new Restaurante[restaurantes.size()]);
    }
}
