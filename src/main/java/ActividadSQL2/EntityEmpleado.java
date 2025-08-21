package ActividadSQL2;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EntityEmpleado {

    private String nombre;
    private String apellidos;
    private String departamento;
    private float sueldo;
    private String diaLibre;
}
