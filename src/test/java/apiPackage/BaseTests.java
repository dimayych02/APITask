package apiPackage;


import org.junit.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class BaseTests {
    protected String url;
    protected String firstPokemon;
    protected String secondPokemon;
    protected String pathToBody;
    protected String ability;
    protected String pathToWeight;

    protected String pathToPokemon;


    @BeforeMethod
    public void setUpApi() {
        url = ConfProperties.getProperty("url");
        firstPokemon = ConfProperties.getProperty("firstPokemon");
        secondPokemon = ConfProperties.getProperty("secondPokemon");
        pathToBody = ConfProperties.getProperty("pathToBody");
        ability = ConfProperties.getProperty("ability");
        pathToWeight = ConfProperties.getProperty("pathToWeight");
        pathToPokemon = ConfProperties.getProperty("pathToPokemon");
        ReqSpecification.installSpecification(ReqSpecification.requestSpec(url)
                , ReqSpecification.responseSpec());
    }

    @Test
    public void checkUsersAbility() {

        Assert.assertNotEquals(Response.checkAbility(firstPokemon, pathToBody, ability)
                , Response.checkAbility(secondPokemon, pathToBody, ability));

    }

    @Test
    public void checkWeightDifference() {
        Assert.assertTrue(Response.checkWeightDiff(firstPokemon, secondPokemon, pathToWeight));


    }

    @Test
    public void checkPokemonName() {
        Assert.assertTrue(Response.getListsOfPokemonOrAbility("", pathToPokemon)
                .stream().limit(15)
                .allMatch(x -> x.getName().length() > 0));
    }
}
