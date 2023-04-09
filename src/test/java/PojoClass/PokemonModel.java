package PojoClass;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private List<Abilities> abilities;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Abilities {
        private Ability ability;


        @Data
        @AllArgsConstructor
        @NoArgsConstructor
        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class Ability {
           private String name;
           private String url;
        }
    }

}