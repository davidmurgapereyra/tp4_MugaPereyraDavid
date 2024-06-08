package ar.edu.unju.fi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.fi.collections.ListadoMaterias;
import ar.edu.unju.fi.model.Materia;

@Controller
public class MateriaController {
    
    @Autowired
    Materia nuevaMateria = new Materia();

    @GetMapping("/formularioMateria")
    public ModelAndView getFormMateria() {
        // Vista formMateria.html
        ModelAndView modelView = new ModelAndView("formMateria");
        modelView.addObject("nuevaMateria", nuevaMateria);
        modelView.addObject("band", false);
        return modelView;
    }

    @PostMapping("/guardarMateria")
    public ModelAndView saveMateria(@ModelAttribute("nuevaMateria") Materia materiaParaGuardar) {
        ListadoMaterias.agregarMateria(materiaParaGuardar);
        ModelAndView modelView = new ModelAndView("listaDeMaterias");
        modelView.addObject("listadoMaterias", ListadoMaterias.listarMaterias());
        return modelView;
    }

    @GetMapping("/modificarMateria/{codigo}")
    public ModelAndView editMateria(@PathVariable(name="codigo") String codigo) {
        Materia materiaParaModificar = ListadoMaterias.buscarMateriaPorCodigo(codigo);
        ModelAndView modelView = new ModelAndView("formMateria");
        modelView.addObject("nuevaMateria", materiaParaModificar);
        modelView.addObject("band", true);
        return modelView;
    }

    @PostMapping("/modificarMateria")
    public ModelAndView updateMateria(@ModelAttribute("nuevaMateria") Materia materiaModificada) {
        ListadoMaterias.modificarMateria(materiaModificada);
        ModelAndView modelView = new ModelAndView("listaDeMaterias");
        modelView.addObject("listadoMaterias", ListadoMaterias.listarMaterias());
        return modelView;
    }

    @GetMapping("/borrarMateria/{codigo}")
    public ModelAndView deleteMateriaDelListado(@PathVariable(name="codigo") String codigo) {
        ListadoMaterias.eliminarMateria(codigo);
        ModelAndView modelView = new ModelAndView("listaDeMaterias");
        modelView.addObject("listadoMaterias", ListadoMaterias.listarMaterias());
        return modelView;
    }
}
