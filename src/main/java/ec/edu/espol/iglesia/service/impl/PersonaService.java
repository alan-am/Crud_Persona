package ec.edu.espol.iglesia.service.impl;


import ec.edu.espol.iglesia.entity.Persona;
import ec.edu.espol.iglesia.exception.ResourceNotFoundException;
import ec.edu.espol.iglesia.repository.IPersonaRepository;
import ec.edu.espol.iglesia.service.IPersonaService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonaService implements IPersonaService {
    private IPersonaRepository personaRepository;

    public PersonaService(IPersonaRepository personaRepository) {
        this.personaRepository = personaRepository;
    }

    @Override
    public Persona guardarPersona(Persona persona) {
        return personaRepository.save(persona);
    }

    @Override ///AQUI
    public Optional<Persona> buscarPorId(String dni) {
        return personaRepository.findById(dni);
    }

    @Override
    public List<Persona> buscarTodos() {
        return personaRepository.findAll();
    }

    @Override
    public void modificarPersona(Persona persona) {
        personaRepository.save(persona);
    }

    @Override
    public void eliminarPersona(String dni) {
        Optional<Persona> personaEncontrado = personaRepository.findById(dni);
        if(personaEncontrado.isPresent()){
            personaRepository.deleteById(dni);
        } else {
            throw new ResourceNotFoundException("La persona "+ dni +" no fue encontrada");
        }

    }

    @Override
    public List<Persona> buscarPorApellidoyNombre(String apellido, String nombre) {
        return null;
    }

    @Override
    public List<Persona> buscarLikeNombre(String nombre) {
        return null;
    }


}
