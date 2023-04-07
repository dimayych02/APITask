package apiPackage;


import PojoClasses.PojoAbility;
import PojoClasses.PojoPokemon;
import com.google.gson.Gson;
import com.google.gson.JsonElement;

import java.util.List;


import static io.restassured.RestAssured.given;

public class RequestToApi {


    public static Object someJsonObject(String endpoint, String pokemon,Class pojoClass) {
        return given().when()
                .get("/"+endpoint+"/"+pokemon)
                .then().extract().jsonPath().getObject("", pojoClass);
    }

    public static JsonElement property(String endpoint,String pokemon,Class pojoClass) {
        return new Gson().toJsonTree(someJsonObject(endpoint,pokemon,pojoClass));
    }

    public static List<PojoPokemon> listOfPokemon() {
        return given()
                .when()
                .get().then()
                .extract().jsonPath()
                .getList("results", PojoPokemon.class);
    }

    public static List<PojoAbility> listOfAbilities(String pokemon) {
        return given()
                .when()
                .get("/pokemon/" + pokemon)
                .then()
                .extract().jsonPath()
                .getList("abilities.ability", PojoAbility.class);
    }
}
