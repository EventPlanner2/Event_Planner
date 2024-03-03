package applicationTest.steps;

import com.example.App;
import com.example.entites.User;
import io.cucumber.java.en.*;
import static org.junit.Assert.*;

public class DeleteEventSteps {


    App application =new App();

    @Given("there is an event with ID {string}")
    public void thereIsAnEventWithID(String string) {

    }
    @When("the user deletes the event with ID {string}")
    public void theUserDeletesTheEventWithID(String string) {

    }
    @Then("the event with ID {string} should be deleted")
    public void theEventWithIDShouldBeDeleted(String string) {

    }

    @Given("there is no event with ID {string}")
    public void thereIsNoEventWithID(String string) {

    }
    @When("the user tries to delete the event with ID {string}")
    public void theUserTriesToDeleteTheEventWithID(String string) {

    }
    @Then("the system should display an error message")
    public void theSystemShouldDisplayAnErrorMessage() {

    }



    @Given("the user provides an invalid event ID")
    public void theUserProvidesAnInvalidEventID() {

    }
    @When("the user tries to delete the event")
    public void theUserTriesToDeleteTheEvent() {

    }


    @Given("there is an event with ID {string} and multiple occurrences")
    public void thereIsAnEventWithIDAndMultipleOccurrences(String string) {

    }

    @Then("all occurrences of the event with ID {string} should be deleted")
    public void allOccurrencesOfTheEventWithIDShouldBeDeleted(String string) {

    }

}
