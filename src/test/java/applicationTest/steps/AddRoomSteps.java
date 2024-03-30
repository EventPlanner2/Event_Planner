package applicationTest.steps;

import com.example.App;
import com.example.entites.User;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import static org.junit.Assert.*;
public class AddRoomSteps {

    App app;
    private String Name;
    private String Available;
    private String Capacity;
    private String CostPerHour;
    private String Description;
    private boolean result;
    private int old_size;

    public AddRoomSteps(App app){
        this.app = app;
        this.Name ="";
        this.Available ="";
        this.Capacity = "";
        this.CostPerHour = "";
        this.Description = "";
    }

    @Given("the system administrator is logged in")
    public void theSystemAdministratorIsLoggedIn() {
       app.loggedInUser = app.loginService.LoginPerformed("Admin","123456");
       app.addRoomService.setLoggedInUser(app.loggedInUser);
    }
    @When("the administrator adds a room with the following details:")
    public void theAdministratorAddsARoomWithTheFollowingDetails(DataTable dataTable) {
        Name = dataTable.cell(1,0) == null ? "" : dataTable.cell(1,0);
        Available = dataTable.cell(1,1) == null ? "" : dataTable.cell(1,1);
        Capacity =  dataTable.cell(1,2) == null ? "" : dataTable.cell(1,2);
        CostPerHour = dataTable.cell(1,3) == null ? "" : dataTable.cell(1,3);
        Description = dataTable.cell(1,4) == null ? "" : dataTable.cell(1,4);

        old_size = app.rooms.size();
        result = app.addRoomService.AddRoomPerformed(Name,Available,Capacity,CostPerHour,Description);

    }
    @Then("the room should be added successfully to the system")
    public void theRoomShouldBeAddedSuccessfullyToTheSystem() {
        assertEquals(app.rooms.size(),old_size+1);
        assertTrue(result);
    }


    @Given("a room with the name {string} already exists")
    public void aRoomWithTheNameAlreadyExists(String string) {
        Name = string;
        assertTrue(app.addRoomService.IsRoomExisted(string));
    }
    @When("the administrator adds a room with the same name")
    public void theAdministratorAddsARoomWithTheSameName(DataTable dataTable) {
        theAdministratorAddsARoomWithTheFollowingDetails(dataTable);
    }
    @Then("the system should display an error indicating that {string}")
    public void theSystemShouldDisplayAnErrorIndicatingThatTheRoomNameIsAlreadyInUse(String string) {
        assertFalse(result);
        assertEquals(app.addRoomService.getMsg(),string);
    }


    @Given("a Service Provider user is logged in with {string} and {string}")
    public void aServiceProviderUserIsLoggedInWithAnd(String string, String string2) {
        app.loggedInUser = app.loginService.LoginPerformed(string,string2);
        app.addRoomService.setLoggedInUser(app.loggedInUser);
    }
    @When("the user tries to add a room")
    public void theUserTriesToAddARoom(DataTable dataTable) {
        theAdministratorAddsARoomWithTheFollowingDetails(dataTable);
    }




}
