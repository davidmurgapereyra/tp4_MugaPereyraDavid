package ar.edu.unju.fi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.fi.collections.ListadoDocentes;
import ar.edu.unju.fi.model.Docente;

@Controller
public class DocenteController {
    
    @Autowired
    Docente nuevoDocente = new Docente();

    @GetMapping("/formularioDocente")
    public ModelAndView getFormDocente() {
        ModelAndView modelView = new ModelAndView("formDocente");
        modelView.addObject("nuevoDocente", nuevoDocente); 
        modelView.addObject("band", false);
        return modelView;
    }
    
    @PostMapping("/guardarDocente")
    public ModelAndView saveDocente(@ModelAttribute("nuevoDocente") Docente docenteParaGuardar) {
        ListadoDocentes.agregarDocente(docenteParaGuardar);
        ModelAndView modelView = new ModelAndView("listaDeDocentes");
        modelView.addObject("listadoDocentes", ListadoDocentes.listarDocentes());  
        return modelView;     
    }
    
    @GetMapping("/modificarDocente/{legajo}")
    public ModelAndView editDocente(@PathVariable(name="legajo") String legajo) {
        Docente docenteParaModificar = ListadoDocentes.buscarDocentePorLegajo(legajo);
        ModelAndView modelView = new ModelAndView("formDocente");
        modelView.addObject("nuevoDocente", docenteParaModificar);   
        modelView.addObject("band", true);
        return modelView;       
    }

    @PostMapping("/modificarDocente")
    public ModelAndView updateDocente(@ModelAttribute("nuevoDocente") Docente docenteModificado) {
        ListadoDocentes.modificarDocente(docenteModificado);
        ModelAndView modelView = new ModelAndView("listaDeDocentes");
        modelView.addObject("listadoDocentes", ListadoDocentes.listarDocentes());   
        return modelView;     
    }
    
    @GetMapping("/borrarDocente/{legajo}")
    public ModelAndView deleteDocenteDelListado(@PathVariable(name="legajo") String legajo) {
        ListadoDocentes.eliminarDocente(legajo);
        ModelAndView modelView = new ModelAndView("listaDeDocentes");
        modelView.addObject("listadoDocentes", ListadoDocentes.listarDocentes());   
        return modelView;       
    }
}
