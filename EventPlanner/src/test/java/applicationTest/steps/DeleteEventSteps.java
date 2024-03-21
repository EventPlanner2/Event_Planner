package applicationTest.steps;

import com.example.App;
import com.example.entites.User;
import io.cucumber.java.en.*;
import static org.junit.Assert.*;

public class DeleteEventSteps {


    App app;
    boolean flag;
    String id;

    public DeleteEventSteps(App app){
        this.app = app;
        flag = false;
        id = "";
    }

    @Given("there is an event with ID {string}")
    public void thereIsAnEventWithID(String string) {
        id = string;
        assertTrue( app.getDeleteUpdateEventService ().DoesEventExists(Integer.parseInt(id)));
    }
    @When("the user deletes the event with ID provided")
    public void theUserDeletesTheEventWithID() {
       flag = app.getDeleteUpdateEventService ().DeleteEventPerform(this.id);
    }
    @Then("the event should be deleted")
    public void theEventWithIDShouldBeDeleted() {

        assertTrue(flag);
    }

    @Given("there is no event with ID {string}")
    public void thereIsNoEventWithID(String string) {
        id = string;
        assertFalse( app.getDeleteUpdateEventService ().DoesEventExists(Integer.parseInt(id)));
    }

    @Then("the system should display an error message {string}")
    public void theSystemShouldDisplayAnErrorMessage(String string) {
        assertEquals( app.getDeleteUpdateEventService ().msg,string);
    }



    @Given("the user provides an invalid event ID {string}")
    public void theUserProvidesAnInvalidEventID(String string) {
        id = string;
    }




}
