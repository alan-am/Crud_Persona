package ec.edu.espol.iglesia.repository;


import ec.edu.espol.iglesia.entity.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPersonaRepository extends JpaRepository<Persona, String> {
    List<Persona> findByApellidoAndNombre(String apellido, String nombre);

    @Query("Select p from Persona p where p.nombre LIKE %:parteNombre% ")
    List<Persona> findByNombreLike(String parteNombre);
}
