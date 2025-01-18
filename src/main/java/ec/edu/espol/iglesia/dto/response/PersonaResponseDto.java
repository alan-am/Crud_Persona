package ec.edu.espol.iglesia.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PersonaResponseDto {
    private String dni;
    private String nombre;
    private String apellido;
}
