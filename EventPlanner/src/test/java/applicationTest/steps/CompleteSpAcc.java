package applicationTest.steps;


import com.example.App;
import com.example.entites.ServiceProvider;
import com.example.entites.User;
import io.cucumber.java.en.*;
import static org.junit.Assert.*;
public class CompleteSpAcc {

    App app;
    private boolean flag_registred;
    private String type;
    private String price;
    private String location;
    private boolean flag_completeAcc;
    private String SPusername;
    public CompleteSpAcc(App app){
        this.app = app;
        SPusername="Amr";
    }


    @Given("the service provider is newly registred")
    public void theServiceProviderIsNewlyRegistred() {
        SPusername="Amr";
        flag_registred= app.getSignUpService ().register(SPusername,"123456789","Amr@Gmail.com","s");

        assertTrue(flag_registred);

    }
    @When("he logs in for the first time")
    public void heLogsInForTheFirstTime() {
        app.setLoggedInUser ( app.getLoginService ().LoginPerformed ( "Amr", "123456789" ) );
    }
    @Then("the system should prompt him to complete his account details \\(location,type,price)")
    public void theSystemShouldPromptHimToCompleteHisAccountDetailsLocationTypePrice() {
        assertTrue( app.getSPAccount ().isCompleteAccount () );
    }

    @Given("the service provider has logged in")
    public void theServiceProviderHasLoggedIn() {
        app.setLoggedInUser ( app.getLoginService ().LoginPerformed ( SPusername, "123456789" ) );
    }
    @When("he completes the account details with valid inputs Location {string} price {string} type {string}")
    public void heCompletesTheAccountDetailsWithValidInputs(String location,String price , String type) {
        this.location = location;
        this.price= price;
        this.type = type;
        flag_completeAcc = app.getSPAccount ().completeAccountPerform(this.location,this.price,this.type);
    }
    @Then("the system should save the information")
    public void theSystemShouldSaveTheInformation() {
        assertTrue(flag_completeAcc);
    }
    @Then("it should not ask for the same details on the second login")
    public void itShouldNotAskForTheSameDetailsOnTheSecondLogin() {
        assertFalse(ServiceProvider.getSPFromData(SPusername).isFirstLogin());
    }


    @When("he provides a invalid location {string}")
    public void heProvidesAInValidLocation(String string) {
        this.type = "Chairs Provider";
        this.location =string;
        this.price ="$99";
        flag_completeAcc = app.getSPAccount ().completeAccountPerform(this.location,this.price,this.type);
    }

    @When("he selects invalid type of service provider {string}")
    public void heSelectsInValidTypeOfServiceProvider(String string) {
        this.type = string;
        this.location ="Nablus";
        this.price ="$99";
        flag_completeAcc = app.getSPAccount ().completeAccountPerform(this.location,this.price,this.type);
    }



    @When("he provides invalid input price like {string}")
    public void heProvidesInvalidInputPriceLike(String string) {
        this.type = "Chairs Provider";
        this.location ="Nablus";
        this.price =string;
        flag_completeAcc = app.getSPAccount ().completeAccountPerform(this.location,this.price,this.type);
    }
    @Then("the system should display error messages {string}")
    public void theSystemShouldDisplayErrorMessages(String string) {
        assertFalse(flag_completeAcc);
        assertEquals( app.getSPAccount ().getCompleteAccountMsg(),string);
    }
}
