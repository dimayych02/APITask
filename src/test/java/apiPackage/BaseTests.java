package apiPackage;


import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.stream.Collectors;


public class BaseTests {

    protected final static String firstPokemon = "rattata";
    protected final static String secondPokemon = "pidgeotto";
    protected final static String ability = "run-away";
    protected final static String url = "https://pokeapi.co/api/v2/pokemon";


    @BeforeMethod
    public void setUpApi() {
        Specifications.installSpecification(Specifications.requestSpec(url), Specifications.responseSpec());
    }


    @Test
    public void checkPokemonName() {
        Assert.assertTrue(
                RequestToApi.listOfPokemon().stream().limit(15).allMatch(x -> x.getName()!= null),
                "Ошибка,одно из имен пустое!");

    }

    @Test
    public void checkPokemonAbility() {
        Assert.assertNotEquals(
                RequestToApi.listOfAbilities(firstPokemon)
                        .stream().map(x -> x.getName()).collect(Collectors.toList()).contains(ability),
                RequestToApi.listOfAbilities(secondPokemon)
                        .stream().map(x -> x.getName()).collect(Collectors.toList()).contains(ability),
                "Ошибка, у них есть общая способность run-away!");

    }
}
