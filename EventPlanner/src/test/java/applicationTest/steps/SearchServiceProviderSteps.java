package applicationTest.steps;

import com.example.App;
import com.example.entites.ServiceProvider;
import com.example.entites.User;
import com.example.services.SearchServiceProvider;
import io.cucumber.java.en.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
public class SearchServiceProviderSteps {

    App app;
    private String location;

    private List<ServiceProvider> checkSPS;
    // dependecy injection
    public SearchServiceProviderSteps(App app){
        this.app = app;
    }

    @Given("the user has selected to search a service provider")
    public void theUserHasSelectedToSearchAServiceProvider() {

        app.getSearchSP ().setSelected ( true );

    }
    @When("the user selects location as the search criteria")
    public void theUserSelectsLocationAsTheSearchCriteria() {

        app.getSearchSP ().setSelectedCriteria("location");

    }
    @When("the user enters {string} as the location")
    public void theUserEntersAsTheLocation(String string) {

        app.getSearchSP ().setLocation(string);

    }
    @When("the user has pressed to search")
    public void theUserHasPressedToSearch() {
        if(checkSPS != null)checkSPS.clear();
        checkSPS = app.getSearchSP ().searchSPPerformed();

    }
    @Then("the application should display a list of service providers in specified location")
    public void theApplicationShouldDisplayAListOfServiceProvidersInSpecifiedLocation() {

        boolean flag = !checkSPS.isEmpty();
        assertTrue(flag);
        for(ServiceProvider s : checkSPS){
            if (!s.getLocation().equals( app.getSearchSP ().getLocation())) {
                flag = false;
                break;
            }
        }

        assertTrue(flag);

    }

    @When("the user selects type as the search criteria")
    public void theUserSelectsTypeAsTheSearchCriteria() {
        app.getSearchSP ().setSelectedCriteria("type");
    }
    @When("the user enters {string} as the service type")
    public void theUserEntersAsTheServiceType(String string) {
        app.getSearchSP ().setType(string);
    }

    @Then("the application should display a list of service providers with specified type")
    public void theApplicationShouldDisplayAListOfServiceProvidersWithSpecifiedType() {
        boolean flag = !checkSPS.isEmpty();
        assertTrue(flag);
        for(ServiceProvider s : checkSPS){
            if (!s.getType().equals( app.getSearchSP ().getType())) {
                flag = false;
                break;
            }
        }

        assertTrue(flag);
    }


    @When("the user selects price as the search criteria")
    public void theUserSelectsPriceAsTheSearchCriteria() {
        app.getSearchSP ().setSelectedCriteria("price");
    }
    @When("the user enters {string} as the maximum price")
    public void theUserEntersAsTheMaximumPrice(String string) {
        app.getSearchSP ().setPrice(string);
    }
    @Then("the application should display a list of service providers with prices up to specified price")
    public void theApplicationShouldDisplayAListOfServiceProvidersWithPricesUpToSpecifiedPrice() {
        boolean flag = !checkSPS.isEmpty();
        assertTrue(flag);
        for(ServiceProvider s : checkSPS){
            if (s.getPrice() > app.getSearchSP ().getPrice()) {
                flag = false;
                break;
            }
        }

        assertTrue(flag);
    }


    @When("the user did not select any criteria")
    public void theUserDidNotSelectAnyCriteria() {
        app.getSearchSP ().setSelectedCriteria("");
    }
    @Then("the application should display an error message says {string}")
    public void theApplicationShouldDisplayAnErrorMessageSays(String string) {
        assertEquals( app.getSearchSP ().getErrorMsg(),string);
    }


    @When("the user has selected an unexisted criteria {string}")
    public void theUserHasSelectedAnUnexistedCriteria(String string) {
        app.getSearchSP ().setSelectedCriteria(string);
    }


    @When("the user enters an invalid location {string}")
    public void theUserEntersAnInvalidLocation(String string) {
        app.getSearchSP ().setLocation(string);
    }

    @When("the user enters an invalid service type {string}")
    public void theUserEntersAnInvalidServiceType(String string) {
        app.getSearchSP ().setType(string);
    }


    @When("the user enters an invalid price {string}")
    public void theUserEntersAnInvalidPrice(String string) {
        app.getSearchSP ().setPrice(string);
    }




}
