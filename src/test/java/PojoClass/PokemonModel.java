package PojoClass;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PokemonModel {
    private int weight;
    private int base_experience;
    private String name;
    private String url;
    private int base_happiness;
    private int capture_rate;
    private List<PokemonModel> results;
    private ArrayList<PokemonModel> abilities;
}
