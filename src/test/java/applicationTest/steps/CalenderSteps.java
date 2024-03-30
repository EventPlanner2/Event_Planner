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
public class CalenderSteps {

    App app;
    private String pastOrFuture;
    private boolean flag;
    private String id;
    private Event returnedEvent;
    private ArrayList<Event> allUpcomingEvents;
    public CalenderSteps(App app){
        this.app = app;
        pastOrFuture = "";
        flag = false;
        id = "";
        allUpcomingEvents = new ArrayList<>();
    }
    @When("the client specify he want to see his {string} events")
    public void theClientSpecifyHeWantToSeeHisEvents(String string) {
        this.pastOrFuture=string;
    }
    @When("the client requests to show his calender")
    public void theClientRequestsToShowHisCalender() {
        app.getCalenderService().clearCalenderEvents();
        flag = app.getCalenderService().calenderPerform(pastOrFuture);
    }
    @Then("all events he booked will be shown with date,event name and id")
    public void allEventsHeBookedWillBeShownWithDateEventNameAndId() {
        for(Event e : app.getCalenderService().getResEvents()){
            if(!e.getUsername().equals(app.getLoggedInUser().getUsername())){
                fail();
            }
        }
        assertTrue(flag);
    }
    @Then("the events must be in the future \\(upcoming events)")
    public void theEventsMustBeInTheFutureUpcomingEvents() {
        for(Event e : app.getCalenderService().getResEvents()){
            if(!e.getStartDate().isAfter(LocalDate.now())){
                fail();
            }
        }

    }

    @When("the client specify the id of event he will see {string}")
    public void theClientSpecifyTheIdOfEventHeWillSee(String string) {
        this.id = string;
    }
    @When("the client requests to show his event")
    public void theClientRequestsToShowHisEvent() {
        returnedEvent = app.getCalenderService().showEventDetails(id);
    }
    @Then("all the details of the event will be shown on the screen")
    public void allTheDetailsOfTheEventWillBeShownOnTheScreen() {
        assertNotNull(returnedEvent);
    }

    @Then("the events must be in the past \\(past events)")
    public void theEventsMustBeInThePastPastEvents() {
        for(Event e : app.getCalenderService().getResEvents()){
            if(!e.getStartDate().isBefore(LocalDate.now())){
                fail();
            }
        }
    }
    @Then("an error message should be displayed {string}")
    public void anErrorMessageShouldBeDisplayed(String string) {
        assertEquals(app.getCalenderService().getMsg(),string);
    }

    @When("the the user requests to show all upcoming events")
    public void theTheUserRequestsToShowAllUpcomingEvents() {
        allUpcomingEvents.addAll(app.getCalenderService().showUpcomingEvents());
    }
    @Then("all upcoming events must be shown on the screen")
    public void allUpcomingEventsMustBeShownOnTheScreen() {
        for(Event e : allUpcomingEvents){
            if(!e.getStartDate().isAfter(LocalDate.now())){
                fail();
            }
        }
    }


}
