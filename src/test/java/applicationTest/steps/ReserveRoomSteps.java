package applicationTest.steps;

import com.example.App;
import com.example.data.RoomData;
import com.example.data.UserData;
import com.example.entites.Event;
import com.example.entites.Room;
import com.example.entites.User;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.junit.Assert.*;
public class ReserveRoomSteps {

    App app;
    ArrayList <Object> arr_res;
    boolean flag;
    Double oldBudget;
    Double adminBudget;
    public ReserveRoomSteps(App app){
        this.app = app;
        arr_res = new ArrayList<>();
        flag =false;
        oldBudget = 0.0;
        adminBudget = 0.0;
    }

    @When("they choose to Reserve Room")
    public void theyChooseToReserveRoom() {

        app.getReserveRoomService ().setLoggedInUser( app.getLoggedInUser () );
        app.getReserveRoomService ().chooseReserveRoom();


    }
    @Then("all incomplete events related to the organizer should be displayed")
    public void allIncompleteEventsRelatedToTheOrganizerShouldBeDisplayed() {
        List<Event> arr = app.getReserveRoomService ().getResEvent();
        for(Event e : arr){
            if(!e.getUsername().equals( app.getLoggedInUser ().getUsername()) || e.isComplete())
                fail();
        }

    }
    @Then("all available rooms should be listed")
    public void allAvailableRoomsShouldBeListed() {
        List <Room> arr = app.getReserveRoomService ().getResRoom();
        for(Room r : arr){
            if(!r.isAvailable())
                fail();
        }
    }

    @When("the organizer enters EventID {string} and RoomID {string} to reserve")
    public void theOrganizerEntersEventIDAndRoomIDToReserve(String string, String string2) {
        oldBudget=app.getLoggedInUser().getBudget();
        adminBudget = UserData.getAdmin().getBudget();
        flag = app.getReserveRoomService ().reserveRoomPerform(string,string2);
    }
    @Then("the room should be reserved for the event and the event become complete")
    public void theRoomShouldBeReservedForTheEvent() {
        assertTrue(flag);
    }
    @Then("a confirmation message should be displayed {string}")
    public void aConfirmationMessageShouldBeDisplayed(String string) {
        if( app.getReserveRoomService ().getMsg ().isEmpty())
            assertEquals( app.getReserveSPService ().getMsg (),string);
        else
            assertEquals( app.getReserveRoomService ().getMsg (),string);
    }


    @When("the organizer enters an invalid EventID {string} and a valid RoomID {string}")
    public void theOrganizerEntersAnInvalidEventIDAndAValidRoomID(String string, String string2) {
        flag = app.getReserveRoomService ().reserveRoomPerform(string,string2);
    }
    @Then("an error message should be displayed indicating {string}")
    public void anErrorMessageShouldBeDisplayedIndicating(String string) {
        if( app.getReserveRoomService ().getMsg ().isEmpty())
            assertEquals( app.getReserveSPService ().getMsg (),string);
        else
            assertEquals( app.getReserveRoomService ().getMsg (),string);
    }


    @When("the organizer enters a valid EventID {string} and an invalid RoomID {string}")
    public void theOrganizerEntersAValidEventIDAndAnInvalidRoomID(String string, String string2) {
        flag = app.getReserveRoomService ().reserveRoomPerform(string,string2);
    }


    @And("the Budget of orgainzer should be decreased")
    public void theBudgetOfOrgainzerShouldBeDecreased() {
        assertTrue(app.getLoggedInUser().getBudget() < oldBudget);
        assertTrue(adminBudget < UserData.getAdmin().getBudget());
    }

    @And("the budget of the orgainzer is lower than the cost")
    public void theBudgetOfTheOrgainzerIsLowerThanTheCost() {
        oldBudget = app.getLoggedInUser().getBudget();
        app.getLoggedInUser().setBudget(50.0);
    }

    @And("the organizer enters a valid {string} for event and {string} for room")
    public void theOrganizerEntersAValidForEventAndForRoom(String string, String string2) {
        flag = app.getReserveRoomService ().reserveRoomPerform(string,string2);
        app.getLoggedInUser().setBudget(oldBudget);
    }
}
