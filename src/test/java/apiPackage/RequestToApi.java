package apiPackage;


import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import java.util.List;


import static io.restassured.RestAssured.given;

public class RequestToApi {


    public static Object valueOfWeight(String userParams, String pathToBody) {
        return given().when()
                .get(userParams)
                .then().extract().jsonPath().getJsonObject(pathToBody);
    }

    public static boolean checkWeightDiff(String userParams1, String userParams2, String pathToBody) {
        return (Integer) valueOfWeight(userParams1, pathToBody) < (Integer) valueOfWeight(userParams2, pathToBody);
    }

    public static List<PojoPokemon> listOfPokemon() {
        return given()
                .when()
                .get().then()
                .extract().jsonPath()
                .getList("results", PojoPokemon.class);

    }

    public static List<PojoAbility> listOfAbilities(String pokemon){
        return given()
                .when()
                .get("/"+pokemon)
                .then()
                .extract().jsonPath()
                .getList("abilities.ability", PojoAbility.class);
    }
}
