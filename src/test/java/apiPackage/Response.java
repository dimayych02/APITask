package apiPackage;


import java.util.List;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;

public class Response {

    public static boolean checkAbility(String userParams, String pathToBody, String ability) {
        return getListsOfPokemonOrAbility(userParams, pathToBody)
                .stream().map(x -> x.getName())
                .collect(Collectors.toList()).contains(ability);
    }

    public static List<Pojo> getListsOfPokemonOrAbility(String userParams, String pathToBody) {
        return given().when()
                .get(userParams)
                .then().extract().jsonPath()
                .getList(pathToBody, Pojo.class);

    }

    public static Object valueOfWeight (String userParams,String pathToBody) {
        return given().when()
                .get(userParams)
                .then().extract().jsonPath().getJsonObject(pathToBody);
    }

    public static boolean checkWeightDiff(String userParams1,String userParams2,String pathToBody){
         return (Integer)valueOfWeight(userParams1,pathToBody)<(Integer)valueOfWeight(userParams2,pathToBody);
    }


}
