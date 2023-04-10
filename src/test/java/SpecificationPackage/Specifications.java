package SpecificationPackage;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Specifications {
    public static RequestSpecification requestSpecPokemon(String url, String basePath, String pokemonName) {
        return new RequestSpecBuilder()
                .setBaseUri(url)
                .setBasePath(basePath)
                .addPathParam("name", pokemonName)
                .setContentType(ContentType.JSON)
                .addFilter(new AllureRestAssured())
                .build();
    }

    public static RequestSpecification requestSpecWithoutPokemon(String url, String basePath) {
        return new RequestSpecBuilder()
                .setBaseUri(url)
                .setBasePath(basePath)
                .setContentType(ContentType.JSON)
                .addFilter(new AllureRestAssured())
                .build();
    }

    public static ResponseSpecification responseSpec() {
        return new ResponseSpecBuilder()
                .expectStatusCode(200)
                .build();
    }

}
