package Ejercicio1;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class CountryLanguage {
    // Entity - CountryLanguage.
    private String countryCode;
    private String language;
    private boolean isOfficial;
    private float percentage;

}
