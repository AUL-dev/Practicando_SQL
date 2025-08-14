package Ejercicio2;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EntityAnimal {

    private String nombre;
    private int edad;
    private String genero;
    private String especie;
    private String ubicacion;
}
