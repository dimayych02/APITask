package apiPackage;




import java.util.List;


import static io.restassured.RestAssured.given;

public class RequestToApi {


    public static Object valueOfWeight(String pokemon) {
        return given().when()
                .get("/"+pokemon)
                .then().extract().jsonPath().getObject("",PojoWeight.class);
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
