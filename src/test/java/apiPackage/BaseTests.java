package apiPackage;

import ConfPropeties.ConfProperties;
import org.testng.Assert;
import org.testng.annotations.Test;


public class BaseTests {

    protected final static String firstPokemon = "rattata";
    protected final static String secondPokemon = "pidgeotto";
    protected final static String ability = "run-away";


    @Test
    public void checkPokemonNameAndLimit() {
        Assert.assertTrue(
                RequestToApi.listOfPokemon(ConfProperties.getProperty("endpointPokemon"), 300).stream().allMatch(x -> x.getName() != null),
                "Ошибка,одно из имен пустое!");
        Assert.assertEquals(RequestToApi.listOfPokemon(ConfProperties.getProperty("endpointPokemon"), 300).size(), 300,
                "Ошибка,список ограничен неверно!");
    }

    @Test
    public void checkPokemonAbility() {
        Assert.assertNotEquals(RequestToApi.pokemonAbilities(ConfProperties.getProperty("pokemonInfo"), firstPokemon)
                        .stream().anyMatch(x -> x.getAbility().getName().contains(ability)),
                RequestToApi.pokemonAbilities(ConfProperties.getProperty("pokemonInfo"), secondPokemon)
                        .stream().anyMatch(x -> x.getAbility().getName().contains(ability)),
                "Ошибка, у них есть общая способность run-away!");
    }


    @Test
    public void checkWeightDifference() {
        Assert.assertTrue(RequestToApi.pokemonModel(ConfProperties.getProperty("pokemonInfo"), firstPokemon)
                        .getWeight() <
                        RequestToApi.pokemonModel(ConfProperties.getProperty("pokemonInfo"), secondPokemon)
                                .getWeight(),
                "Ошибка,вес первого покемона больше,чем второго!");
    }

    @Test
    public void comparePokemonExperience() {
        Assert.assertTrue(RequestToApi.pokemonModel(ConfProperties.getProperty("pokemonInfo"), firstPokemon).getBase_experience() <
                        RequestToApi.pokemonModel(ConfProperties.getProperty("pokemonInfo"), secondPokemon).getBase_experience(),
                "Ошибка,боевой опыт первого покемона больше второго!");
    }

    @Test
    public void sumAttackPotential() {
        Assert.assertTrue(RequestToApi.pokemonModel(ConfProperties.getProperty("endpointPokemonSpecies"), firstPokemon)
                        .getBase_happiness()
                        + RequestToApi.pokemonModel(ConfProperties.getProperty("endpointPokemonSpecies"), firstPokemon)
                        .getCapture_rate() >
                        RequestToApi.pokemonModel(ConfProperties.getProperty("endpointPokemonSpecies"), secondPokemon)
                                .getBase_happiness() +
                                RequestToApi.pokemonModel(ConfProperties.getProperty("endpointPokemonSpecies"), secondPokemon)
                                        .getCapture_rate(),
                "Ошибка,боевая мощь второго покемона больше!");
    }

}