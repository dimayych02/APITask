package apiPackage;


import PojoClass.PokemonModel;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class RequestToApi {


    public static Response response(String endpoint) {
        return given()
                .when()
                .basePath(endpoint)
                .get()
                .then().extract().response();
    }

    public static PokemonModel pokemonModel(String endpoint) {
        return response(endpoint).as(PokemonModel.class);
    }
}
