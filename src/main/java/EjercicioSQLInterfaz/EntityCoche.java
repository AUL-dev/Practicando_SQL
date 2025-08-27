package EjercicioSQLInterfaz;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EntityCoche {
    private String matricula;
    private String marca;
    private String modelo;
    private float precio;
    private String extras;
}
