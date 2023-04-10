package apiPackage;


import ConfPropeties.ConfProperties;
import PojoClass.PokemonModel;
import SpecificationPackage.Specifications;
import io.restassured.response.Response;


import java.util.List;

import static io.restassured.RestAssured.given;

public class RequestToApi {


    public static Response response(String basePath,String pokemon) {
        return given()
                .spec(Specifications.requestSpecWithPathParam(ConfProperties.getProperty("url"),basePath,pokemon))
                .get()
                .then()
                .spec(Specifications.responseSpec())
                .extract().response();
    }



    public static List<PokemonModel> listOfPokemon(String basePath,int queryParamLimit) {
        return given()
                .spec(Specifications.requestSpecWithQueryParam(ConfProperties.getProperty("url"),basePath,queryParamLimit))
                .get()
                .then()
                .spec(Specifications.responseSpec())
                .extract().as(PokemonModel.class).getResults();
    }

    public static PokemonModel pokemonModel(String endpoint, String pokemon) {
        return response(endpoint, pokemon).as(PokemonModel.class);
    }


    public static List<PokemonModel.Abilities> pokemonAbilities(String endpoint, String pokemon){
        return response(endpoint, pokemon).as(PokemonModel.class).getAbilities();
    }
}
