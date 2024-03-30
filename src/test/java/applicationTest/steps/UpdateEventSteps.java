package applicationTest.steps;
import com.example.App;
import com.example.entites.User;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import static org.junit.Assert.*;
public class UpdateEventSteps {


    App app;
    String id;
    private String username;
    private String password;
    private int eventId;
    private String name;
    private String description;
    private String startDate;
    private String endDate;
    private String startClock;
    private String endClock;
    private String attendeeCount;
    private String imagePath;
    boolean flag;
    public UpdateEventSteps(App app){
        this.app = app;
        this.id ="";
        this.username = "Ali Turabi";
        this.password = "123456789";
        this.eventId =0;
        this.name = "";
        this.description = "";
        this.startDate = "";
        this.endDate = "";
        this.startClock = "";
        this.endClock = "";
        this.attendeeCount = "";
        this.imagePath = "";
    }


    @When("the user enters an id to update {string}")
    public void theUserEntersAnIdToUpdate(String string) {
        this.id = string;
    }
    @When("the user enters the new event information")
    public void theUserEntersTheNewEventInformation(DataTable dataTable) {
        username = dataTable.cell(0,1);
        name = dataTable.cell(1,1);
        description = dataTable.cell(2,1);
        startDate = dataTable.cell(3,1);
        endDate = dataTable.cell(4,1);
        startClock = dataTable.cell(5,1);
        endClock = dataTable.cell(6,1);
        attendeeCount = dataTable.cell(7,1);
        imagePath = dataTable.cell(8,1);
    }
    @When("the user updates the event with ID provided")
    public void theUserUpdatesTheEventWithIDProvided() {
        flag = app.deleteUpdateEventService.UpdateEventPerform(id,username,name,description,startDate,endDate,startClock,endClock,attendeeCount,imagePath);

    }


    @Then("the event should be updated")
    public void theEventShouldBeUpdated() {

        assertTrue(flag);

    }
}
