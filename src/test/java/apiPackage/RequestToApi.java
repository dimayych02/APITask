package ApiPackage;


import PojoClass.PokemonModel;
import io.restassured.response.Response;


import java.util.List;

import static io.restassured.RestAssured.given;

public class RequestToApi {


    public static Response response(String endpoint, String pokemon) {
        return given()
                .when()
                .pathParam("endpoint", endpoint)
                .pathParam("name", pokemon)
                .get("/{endpoint}/{name}")
                .then().extract().response();
    }

    public static PokemonModel pokemonModel(String endpoint, String pokemon) {
        return response(endpoint, pokemon).as(PokemonModel.class);
    }
   public static List<PokemonModel> listOfAbilities(String endpoint, String pokemon){
        return response(endpoint,pokemon).jsonPath().getList("abilities.ability", PokemonModel.class);
   }

}
