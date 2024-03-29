package applicationTest.steps;
import com.example.App;
import com.example.entites.Client;
import com.example.entites.User;
import com.example.services.UpgradeClient;
import io.cucumber.java.en.*;
import static org.junit.Assert.*;

public class upgradeClientSteps {

    App app;

    UpgradeClient service;
    // dependency injection
    public upgradeClientSteps(App app){
        this.app = app;
        this.service = new UpgradeClient( app.getLoggedInUser () );
    }
    @Given("the user is logged in as a client")
    public void theUserIsLoggedInAsAClient() {
        app.setLoggedInUser ( app.getLoginService ().loginPerformed ( "Ahmad", "Ahmad12345" ) );
        assertEquals('c', (char) app.getLoggedInUser ().getRole());
    }
    @When("the user selects Upgrade your account from the menu")
    public void theUserSelectsUpgradeYourAccountFromTheMenu() {
        service.setLoggedInUser( app.getLoggedInUser () );
        service.upgradeClientPerform();
    }
    @Then("the account should be successfully upgraded to organizer status")
    public void theAccountShouldBeSuccessfullyUpgradedToOrganizerStatus() {
        try {
            boolean f = Client.getClientFromData( app.getLoggedInUser ().getUsername()).isOrganizer();
            assertTrue(f);
        }
        catch (NullPointerException e){
            fail();
        }

    }
    @Then("the user should receive a confirmation message {string}")
    public void theUserShouldReceiveAConfirmationMessage(String string) {
        assertEquals(service.getMsg(),string);
    }


    @When("then cancels the upgrade process")
    public void thenCancelsTheUpgradeProcess() {
        service.cancelUpgradeClientPerform();
    }
    @Then("the account should remain as a client")
    public void theAccountShouldRemainAsAClient() {
        try {
            boolean f = Client.getClientFromData( app.getLoggedInUser ().getUsername()).isOrganizer();
            assertFalse(f);
        }
        catch (NullPointerException e){
            fail();
        }
    }


    @Given("the user is not logged in")
    public void theUserIsNotLoggedIn() {
        app.setLoggedInUser ( null );
    }

    @Then("the system should prompt the user to log in first {string}")
    public void theSystemShouldPromptTheUserToLogInFirst(String string) {
        assertEquals(service.getMsg(),string);
    }

    @Given("the user is logged in as an organizer")
    public void theUserIsLoggedInAsAnOrganizer() {
        app.setLoggedInUser ( app.getLoginService ().loginPerformed ( "Ahmad", "Ahmad12345" ) );
    }


    @Given("the user is logged in as a {string} with {string} with {string} with {string}")
    public void theUserIsLoggedInAsAWithWithWith(String string, String string2, String username, String pass) {
        app.setLoggedInUser ( app.getLoginService ().loginPerformed ( username, pass ) );
    }

    @Then("the system should inform the user with invalid role {string}")
    public void theSystemShouldInformTheUserWith(String string) {
        assertEquals(service.getMsg(),string);
    }

    @Then("the system should inform the user that {string}")
    public void theSystemShouldInformTheUserThat(String string) {
        assertEquals(service.getMsg(),string);
    }


}
