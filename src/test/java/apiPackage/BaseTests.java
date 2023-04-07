package apiPackage;


import LogApiListener.LogListener;
import PojoClasses.PojoAttackPerformance;
import PojoClasses.PojoWeight;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.stream.Collectors;

@Listeners(LogListener.class)
public class BaseTests {

    protected final static String firstPokemon = "rattata";
    protected final static String secondPokemon = "pidgeotto";
    protected final static String endpointPokemon = "pokemon";
    protected final static String endpointPokemonSpecies = "pokemon-species";
    protected final static String ability = "run-away";
    protected final static String url = "https://pokeapi.co/api/v2";

    @BeforeMethod
    public void setUpApi() {
        Specifications.installSpecification(Specifications.requestSpec(url), Specifications.responseSpec());
    }



    @Test
    public void checkPokemonName() {
        Assert.assertTrue(
                RequestToApi.listOfPokemon().stream().limit(15).allMatch(x -> x.getName() != null),
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

    @Test
    public void checkWeightDifference() {
        Assert.assertTrue(RequestToApi.property(endpointPokemon, firstPokemon, PojoWeight.class)
                        .getAsJsonObject().get("weight").getAsInt() <
                        RequestToApi.property(endpointPokemon, secondPokemon, PojoWeight.class)
                                .getAsJsonObject().get("weight").getAsInt(),
                "Ошибка,вес первого покемона больше,чем первого!");
    }

    @Test
    public void comparePokemonExperience() {
        Assert.assertTrue(RequestToApi.property(endpointPokemon, firstPokemon, PojoWeight.class).getAsJsonObject().get("base_experience").getAsInt() <
                        RequestToApi.property(endpointPokemon, secondPokemon, PojoWeight.class).getAsJsonObject().get("base_experience").getAsInt(),
                "Ошибка,боевой опыт первого покемона больше второго!");
    }

    @Test
    public void sumAttackPotential() {
        Assert.assertTrue(RequestToApi.property(endpointPokemonSpecies, firstPokemon, PojoAttackPerformance.class)
                        .getAsJsonObject().get("base_happiness").getAsInt()
                        + RequestToApi.property(endpointPokemonSpecies, firstPokemon, PojoAttackPerformance.class)
                        .getAsJsonObject().get("capture_rate").getAsInt() >
                        RequestToApi.property(endpointPokemonSpecies, secondPokemon, PojoAttackPerformance.class)
                                .getAsJsonObject().get("base_happiness").getAsInt() +
                                RequestToApi.property(endpointPokemonSpecies, secondPokemon, PojoAttackPerformance.class)
                                        .getAsJsonObject().get("capture_rate").getAsInt(),
                "Ошибка,боевая мощь второго покемона больше!");
    }
}