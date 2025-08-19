package ActividadSQL;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EntityAlumno {
    private String nombre;
    private String apellidos;
    private String curso;
    private float notaFinal;
    private String observaciones;
}
