package applicationTest.steps;

import com.example.App;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.*;

public class DeleteRoomSteps {

    App app;
    boolean flag;
    String id;

    public DeleteRoomSteps(App app){
        this.app = app;
        flag = false;
        id = "";
    }
    @Given("there is an Room with ID {string}")
    public void thereIsAnRoomWithID(String string) {
        id = string;
        assertTrue(app.deleteRoom.doesRoomExists(Integer.parseInt(id)));
    }
    @When("the user deletes the Room with ID provided")
    public void theUserDeletesTheRoomWithID() {
        flag = app.deleteRoom.deleteRoomPerform(this.id);
    }
    @Then("the Room should be deleted")
    public void theRoomWithIDShouldBeDeleted() {

        assertTrue(flag);
    }
    @Given("there is no Room with ID {string}")
    public void thereIsNoRoomWithID(String string) {
        id = string;
        assertFalse(app.deleteRoom.doesRoomExists(Integer.parseInt(id)));
    }
    @Then("the system should display an error message {string}")
    public void theSystemShouldDisplayAnErrorMessage(String string) {
        assertEquals(app.deleteRoom.msg,string);
    }

    @Given("the user provides an invalid Room ID {string}")
    public void theUserProvidesAnInvalidRoomID(String string) {
        id = string;
    }

}
