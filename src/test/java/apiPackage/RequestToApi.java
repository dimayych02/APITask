package apiPackage;


import PojoClass.PokemonModel;
import io.restassured.response.Response;


import java.util.List;

import static io.restassured.RestAssured.given;

public class RequestToApi {


    public static Response response(String endpoint, String pokemon) {
        return given()
                .pathParam("endpoint", endpoint)
                .pathParam("name", pokemon)
                .get("/{endpoint}/{name}")
                .then().extract().response();
    }


    public static List<PokemonModel> listOfPokemon(String endpoint,int queryParamLimit) {
        return given()
                .pathParam("endpoint", endpoint)
                .queryParam("limit",queryParamLimit)
                .get("/{endpoint}")
                .then().extract().as(PokemonModel.class).getResults();
    }

    public static PokemonModel pokemonModel(String endpoint, String pokemon) {
        return response(endpoint, pokemon).as(PokemonModel.class);
    }


    public static List<PokemonModel.Abilities> pokemonAbilities(String endpoint, String pokemon){
        return response(endpoint, pokemon).as(PokemonModel.class).getAbilities();
    }
}
