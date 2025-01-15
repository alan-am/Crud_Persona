package ec.edu.espol.iglesia.repository;

import ec.edu.espol.iglesia.entity.Domicilio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDomicilioRepository extends JpaRepository <Domicilio, Integer>{
}
