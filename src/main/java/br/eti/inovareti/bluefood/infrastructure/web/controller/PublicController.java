package br.eti.inovareti.bluefood.infrastructure.web.controller;

import br.eti.inovareti.bluefood.application.ClienteService;
import br.eti.inovareti.bluefood.domain.cliente.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/public/cliente")
public class PublicController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping(path = "new")
    public String newCliente(Model model) {
        model.addAttribute("cliente", new Cliente());
        ControllerHelper.setEditMode(model, true);
        return "cliente-cadastro";
    }

    @PostMapping(path = "save")
    public String saveCliente(@ModelAttribute("cliente") Cliente cliente) {
        clienteService.saveCliente(cliente);
        return "cliente-cadastro";
    }
}