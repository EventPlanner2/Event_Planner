package applicationTest.steps;
import com.example.App;
import com.example.data.EventData;
import com.example.entites.Client;
import com.example.entites.Event;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ArrayList;

import static com.example.data.EventData.getEvents;
import static org.junit.Assert.*;
public class BookEventSteps {

    App app;
    ArrayList<Event> res;
    String id;
    boolean flag;
    List<Event> tmp;
    public BookEventSteps(App app){
        this.app = app;
        id = "";
        flag = false;
        tmp = new ArrayList<>();
    }
    @Given("the client user is logged in with their account")
    public void theClientUserIsLoggedInWithTheirAccount() {
      
         app.setLoggedInUser ( app.getLoginService ().loginPerformed ( "Ali Turabi", "123456789" ) );
        //app.loggedInUser = app.loginService.LoginPerformed("Ali Turabi","123456789");
        app.getCalenderService().setLoggedInUser(app.getLoggedInUser());
        

    }
    @Given("the user is a client")
    public void theUserIsAClient() {
        assertEquals('c', (char) app.getLoggedInUser ().getRole());
    }
    @When("the client choose to book an event")
    public void theClientChooseToBookAnEvent() {
        res = app.getBookEventService ().chooseBookEvent();
    }
    @Then("the client should see a list of upcoming events")
    public void theClientShouldSeeAListOfUpcomingEvents() {
        LocalDate dateNow = LocalDate.now();
        for(Event e : res){
            if(!e.getStartDate().isAfter(dateNow)){
                fail();
            }
        }
    }

    @When("the client chooses an event by its ID {string}")
    public void theClientChoosesAnEventByItsID(String string) {
        id = string;
    }
    @When("the client requests to book an event")
    public void theClientRequestsToBookAnEvent() {
        flag = app.getBookEventService().bookEventPerform(id, app.getLoggedInUser ().getUsername());
    }
    @Then("the client should see a confirmation message {string}")
    public void theClientShouldSeeAConfirmationMessage(String string) {
        assertEquals( app.getBookEventService ().getMsg (),string);
    }
    @Then("the client should receive an email confirming the booking")
    public void theClientShouldReceiveAnEmailConfirmingTheBooking() {
        assertTrue( app.getBookEventService ().isEmail_sent () );
    }

    @When("the client enters an invalid event ID {string}")
    public void theClientEntersAnInvalidEventID(String string) {
        id = string;
    }
    @Then("the client should see an error message {string}")
    public void theClientShouldSeeAnErrorMessage(String string) {
        assertEquals( app.getBookEventService ().getMsg (),string);

    }


    @When("the client does not select any event")
    public void theClientDoesNotSelectAnyEvent() {
        id = "";
    }


    @Given("there are no upcoming events")
    public void thereAreNoUpcomingEvents() {
        tmp.addAll( EventData.getEvents());
        EventData.getEvents().clear();
    }
    @Then("the client should see a message {string}")
    public void theClientShouldSeeAMessage(String string) {
        assertEquals( app.getBookEventService ().getMsg (),string);
        EventData.getEvents().addAll(tmp);
    }
    @When("the user logs in as a role not client")
    public void theUserLogsInAsARoleNotClient(){
        app.logoutPerform();
        app.setLoggedInUser ( app.getLoginService ().loginPerformed ( "Admin", "123456" ) );
    }

}
