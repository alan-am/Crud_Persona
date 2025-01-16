package ec.edu.espol.iglesia.service;


import ec.edu.espol.iglesia.entity.Persona;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface IPersonaService {
    Persona guardarPersona(Persona persona);

    Optional<Persona> buscarPorId(Integer id);

    List<Persona> buscarTodos();

    void modificarPersona(Persona persona);
    void eliminarPersona(Integer id);

    List<Persona> buscarPorApellidoyNombre(String apellido, String nombre);
    @Query("Select p from Persona p where p.nombre LIKE %:nombre%") //!REVISAR
    List<Persona> buscarLikeNombre(String nombre);

    // select * from pacientes where nombre like CONCAT('%',variable,'%'); //!OJO
}
