package br.eti.inovareti.bluefood.infrastructure.web.controller;

import br.eti.inovareti.bluefood.application.service.ClienteService;
import br.eti.inovareti.bluefood.application.service.RestauranteService;
import br.eti.inovareti.bluefood.application.service.ValidationException;
import br.eti.inovareti.bluefood.domain.cliente.Cliente;
import br.eti.inovareti.bluefood.domain.restaurante.CategoriaRestauranteRepository;
import br.eti.inovareti.bluefood.domain.restaurante.Restaurante;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping(path = "public")
public class PublicController {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private RestauranteService restauranteService;

    @Autowired
    private CategoriaRestauranteRepository categoriaRestauranteRepository;

    @GetMapping(path = "cliente/new")
    public String newCliente(Model model) {
        model.addAttribute("cliente", new Cliente());
        ControllerHelper.setEditMode(model, false);
        return "cliente-cadastro";
    }

    @GetMapping(path = "restaurante/new")
    public String newRestaurante(Model model) {
        model.addAttribute("restaurante", new Restaurante());
        ControllerHelper.setEditMode(model, false);
        ControllerHelper.addCategoriasToRequest(categoriaRestauranteRepository, model);
        return "restaurante-cadastro";
    }

    @PostMapping(path = "cliente/save")
    public String saveCliente(@ModelAttribute("cliente") @Valid Cliente cliente, Errors errors, Model model) {
        if (!errors.hasErrors()) {
            try {
                clienteService.saveCliente(cliente);
                model.addAttribute("msg", "Cliente gravado com sucesso!");
            } catch (ValidationException e) {
                errors.rejectValue("email", null, e.getMessage());
            }
        }

        ControllerHelper.setEditMode(model, false);
        return "cliente-cadastro";
    }

    @PostMapping(path = "restaurante/save")
    public String saveRestaurante(@ModelAttribute("restaurante") @Valid Restaurante restaurante, Errors errors, Model model) {
        if (!errors.hasErrors()) {
            try {
                restauranteService.saveRestaurante(restaurante);
                model.addAttribute("msg", "Restaurante gravado com sucesso!");
            } catch (ValidationException e) {
                errors.rejectValue("email", null, e.getMessage());
            }
        }

        ControllerHelper.setEditMode(model, false);
        ControllerHelper.addCategoriasToRequest(categoriaRestauranteRepository, model);
        return "restaurante-cadastro";
    }
}