package br.eti.inovareti.bluefood.infrastructure.web.controller;

import br.eti.inovareti.bluefood.domain.restaurante.CategoriaRestaurante;
import br.eti.inovareti.bluefood.domain.restaurante.CategoriaRestauranteRepository;
import org.springframework.data.domain.Sort;
import org.springframework.ui.Model;

import java.util.List;

public class ControllerHelper {

    public static void setEditMode(Model model, Boolean isEdit) {
        model.addAttribute("editMode", isEdit);
    }

    public static void addCategoriasToRequest(CategoriaRestauranteRepository repository, Model model) {
        List<CategoriaRestaurante> categorias = repository.findAll(Sort.by(Sort.Direction.ASC, "nome"));
        model.addAttribute("categorias", categorias);
    }
}
