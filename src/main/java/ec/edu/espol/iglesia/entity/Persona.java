package ec.edu.espol.iglesia.entity;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "personas")
public class Persona {

    @Id
    @NotBlank
    @Size(min=7, max=15)
    private String cedula;

    @NotBlank
    private String apellido;
    @NotBlank
    private String nombre;
    @NotNull
    private LocalDate fechaNacimiento;
    @NotNull
    private String sexo;

    @Valid
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_domicilio")
    private Domicilio domicilio;

    @Valid
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_telefono")
    private Telefono telefono;


    @Override
    public String toString() {
        return "Persona{" +
                "cedula='" + cedula + '\'' +
                ", apellido='" + apellido + '\'' +
                ", nombre='" + nombre + '\'' +
                ", fechaNacimiento=" + fechaNacimiento +
                ", Sexo=" + sexo +
                ", domicilio=" + domicilio +
                ", telefono=" + telefono +
                '}';
    }
}
