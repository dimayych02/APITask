package apiPackage;


import ConfPropeties.ConfProperties;
import LogApiListener.ApiListener;
import SpecificationPackage.Specifications;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class BaseTests extends ApiListener {

    protected final static String firstPokemon = "rattata";
    protected final static String secondPokemon = "pidgeotto";
    protected final static String endpointPokemon = "pokemon";
    protected final static String endpointPokemonSpecies = "pokemon-species";
    protected final static String ability = "run-away";


    @BeforeMethod
    public void setUpApi() {
        Specifications.installSpecification(Specifications.requestSpec(ConfProperties.getProperty("url")),
                Specifications.responseSpec());
    }


    @Test
    public void checkPokemonName() {
        Assert.assertTrue(
                RequestToApi.pokemonModel(endpointPokemon, "").getResults().stream().allMatch(x -> x.getName() != null),
                "Ошибка,одно из имен пустое!");
    }

    @Test
    public void checkPokemonAbility() {
        Assert.assertNotEquals(RequestToApi.listOfAbilities(endpointPokemon, firstPokemon)
                        .stream().anyMatch(x->x.getName().contains(ability)),
                RequestToApi.listOfAbilities(endpointPokemon, secondPokemon)
                        .stream().anyMatch(x->x.getName().contains(ability)),
                "Ошибка, у них есть общая способность run-away!");
    }

    @Test
    public void checkWeightDifference() {
        Assert.assertTrue(RequestToApi.pokemonModel(endpointPokemon, firstPokemon)
                        .getWeight() <
                        RequestToApi.pokemonModel(endpointPokemon, secondPokemon)
                                .getWeight(),
                "Ошибка,вес первого покемона больше,чем первого!");
    }

    @Test
    public void comparePokemonExperience() {
        Assert.assertTrue(RequestToApi.pokemonModel(endpointPokemon, firstPokemon).getBase_experience() <
                        RequestToApi.pokemonModel(endpointPokemon, secondPokemon).getBase_experience(),
                "Ошибка,боевой опыт первого покемона больше второго!");
    }

    @Test
    public void sumAttackPotential() {
        Assert.assertTrue(RequestToApi.pokemonModel(endpointPokemonSpecies, firstPokemon)
                        .getBase_happiness()
                        + RequestToApi.pokemonModel(endpointPokemonSpecies, firstPokemon)
                        .getCapture_rate() >
                        RequestToApi.pokemonModel(endpointPokemonSpecies, secondPokemon)
                                .getBase_happiness() +
                                RequestToApi.pokemonModel(endpointPokemonSpecies, secondPokemon)
                                        .getCapture_rate(),
                "Ошибка,боевая мощь второго покемона больше!");
    }

}