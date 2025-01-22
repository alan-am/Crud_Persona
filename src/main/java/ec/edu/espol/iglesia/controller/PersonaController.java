package ec.edu.espol.iglesia.controller;

import ec.edu.espol.iglesia.entity.Persona;
import ec.edu.espol.iglesia.service.impl.PersonaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/persona") //endpoint
public class PersonaController {
    private PersonaService personaService;

    public PersonaController(PersonaService personaService) {
        this.personaService = personaService;
    }
    // ingresa -> JSON -> jackson -> Objeto Persona  //OJO
    // salga -> Objeto Persona -> jackson -> JSON    //OJO
    @PostMapping("/guardar")
    public ResponseEntity<Persona> guardarPersona(@Valid @RequestBody Persona persona){
        return ResponseEntity.ok(personaService.guardarPersona(persona));
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable String cedula){
        Optional <Persona> persona = personaService.buscarPorId(cedula);
        if(persona.isPresent()){
            return ResponseEntity.ok(persona.get());
        } else {
            //ResponseEntity.notFound().build(); //OJO
            return ResponseEntity.status(HttpStatusCode.valueOf(404)).build();
        }
    }

    @GetMapping("/buscartodos")
    public ResponseEntity<List<Persona>> buscarTodos(){
        return ResponseEntity.ok(personaService.buscarTodos());
    }

    @PutMapping("/modificar")
    public ResponseEntity<?> modificarPersona(@Valid @RequestBody Persona persona){
        Optional <Persona> personaEncontrado = personaService.buscarPorId(persona.getCedula());
        if(personaEncontrado.isPresent()){
            personaService.modificarPersona(persona);
            String jsonResponse = "{\"mensaje\": \"La persona fue modificada\"}";
            return ResponseEntity.ok(jsonResponse);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/eliminar/{cedula}")
    public ResponseEntity<?> eliminarPersona(@PathVariable String cedula){
            personaService.eliminarPersona(cedula);
            return ResponseEntity.ok("{\"mensaje\": \"La persona fue borrada\"}");
    }

    @GetMapping("/buscarApellidoNombre")
    public ResponseEntity<List<Persona>> buscarApellidoYNombre(@RequestParam String apellido,
                                                                @RequestParam String nombre){
        return ResponseEntity.ok(personaService.buscarPorApellidoyNombre(apellido, nombre));
    }

    @GetMapping("/buscarNombre/{nombre}")
    public ResponseEntity<List<Persona>> buscarNombreLike(@PathVariable String nombre){
        return ResponseEntity.ok(personaService.buscarLikeNombre(nombre));
    }
}

