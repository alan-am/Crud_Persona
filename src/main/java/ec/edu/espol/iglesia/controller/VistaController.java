package ec.edu.espol.iglesia.controller;

import ec.edu.espol.iglesia.entity.Persona;
import ec.edu.espol.iglesia.service.impl.PersonaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class VistaController {
    private PersonaService personaService;

    public VistaController(PersonaService personaService) {
        this.personaService = personaService;
    }

    // localhost:8080/20  -> @PathVariable
    // localhost:8080?id=1  -> @RequestParams
    @GetMapping("/index")
    public String mostrarPersonaPorId(Model model, @RequestParam Integer id){
        Persona persona = personaService.buscarPorId(id).get();
        model.addAttribute("nombre", persona.getNombre());
        model.addAttribute("apellido", persona.getApellido());
        return "persona";
    }

    @GetMapping("/index2/{id}")
    public String mostrarPersonaPorId2(Model model, @PathVariable Integer id){
        Persona persona = personaService.buscarPorId(id).get();
        model.addAttribute("nombre", persona.getNombre());
        model.addAttribute("apellido", persona.getApellido());
        return "persona";
    }
}
