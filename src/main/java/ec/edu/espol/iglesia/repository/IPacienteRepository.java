package ec.edu.espol.iglesia.repository;

import ec.edu.espol.iglesia.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPacienteRepository extends JpaRepository<Paciente, Integer> {
    List<Paciente> findByApellidoAndNombre(String apellido, String nombre);

    @Query("Select p from Paciente p where p.nombre LIKE %:parteNombre% ")
    List<Paciente> findByNombreLike(String parteNombre);
}
