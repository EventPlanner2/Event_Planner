package applicationTest.steps;
import com.example.App;
import com.example.data.RoomData;
import com.example.entites.User;
import io.cucumber.java.en.*;
import static org.junit.Assert.*;
public class DeleteRoomSteps {

    App app;
    private boolean flag;
    private String id;
    private int old_size;
    public DeleteRoomSteps(App app){
        this.app = app;
        flag = false;
        id = "";
    }
    @Given("there is a room with ID {string}")
    public void thereIsARoomWithID(String string) {
        id = string;
        assertTrue(app.getDeleteRoomService().DoesRoomExists(Integer.parseInt(string)));
    }
    @When("the admin deletes the room with ID provided")
    public void theAdminDeletesTheRoomWithIDProvided() {
        old_size = RoomData.getRooms().size();
        flag = app.getDeleteRoomService().DeleteRoomPerform(id);
    }
    @Then("the room should be deleted")
    public void theRoomShouldBeDeleted() {
        assertEquals(old_size-1,RoomData.getRooms().size());
    }


    @Given("there is no room with ID {string}")
    public void thereIsNoRoomWithID(String string) {
        id = string;
        assertFalse(app.getDeleteRoomService().DoesRoomExists(Integer.parseInt(string)));
    }
    @Then("the system should display an error message room {string}")
    public void theSystemShouldDisplayAnErrorMessageRoom(String string) {
       assertEquals(app.getDeleteRoomService().getMsg(),string);
    }


    @Given("the admin provides an invalid room ID {string}")
    public void theAdminProvidesAnInvalidRoomID(String string) {
        id = string;
    }

}
