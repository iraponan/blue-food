package br.eti.inovareti.bluefood.infrastructure.web.controller;

import br.eti.inovareti.bluefood.domain.cliente.Cliente;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/public")
public class PublicController {

    @GetMapping("/cliente/new")
    public String newCliente(Model model) {
        model.addAttribute("cliente", new Cliente());
        return "cliente-cadastro";
    }
}
