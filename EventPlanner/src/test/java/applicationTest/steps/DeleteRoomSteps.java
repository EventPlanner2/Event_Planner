package applicationTest.steps;

import com.example.App;
import com.example.entites.User;
import io.cucumber.java.en.*;
import static org.junit.Assert.*;


public class DeleteRoomSteps {

    App app;

    boolean flag;

    String id;


    public DeleteRoomSteps(App app) {
        this.app = app;
        id = "";
        flag = false;
    }

    @Given("there is an room with ID {string}")
    public void there_is_an_room_with_id(String string) {
        assertTrue(app.deleteRoomService.doesRoomExists(222));
    }

    @When("the user deletes the room with ID provided")
    public void the_user_deletes_the_room_with_id_provided() {
        flag = app.deleteUpdateEventService.DeleteEventPerform(this.id);
    }

    @Then("the room should be deleted")
    public void the_room_should_be_deleted() {
        assertTrue(flag);
    }

    @Given("there is no room with ID {string}")
    public void there_is_no_room_with_id(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Given("the user provides an invalid room ID {string}")
    public void the_user_provides_an_invalid_room_id(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }


}
