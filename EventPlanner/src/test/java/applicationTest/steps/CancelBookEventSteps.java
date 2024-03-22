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

import static org.junit.Assert.*;

public class CancelBookEventSteps {

    App app;
    private ArrayList<Event> eventsBooked;
    private String id;
    private boolean flag;
    public CancelBookEventSteps(App app){
        this.app = app;
        eventsBooked = new ArrayList<>();
        id = "";
        flag =false;
    }

    @When("the client chooses choice cancel booking event")
    public void theClientChoosesChoiceCancelBookingEvent() {
        eventsBooked.addAll(app.getBookEventService().chooseCancelBookEvent(app.getLoggedInUser().getUsername()));
    }
    @Then("all upcoming events he booked will be shown with date,event name and id")
    public void allUpcomingEventsHeBookedWillBeShownWithDateEventNameAndId() {
        for(Event e : eventsBooked){
            if(!e.getStartDate().isAfter(LocalDate.now())){
                fail();
            }
        }
    }

    @When("the client specify the id of the the event he booked {string}")
    public void theClientSpecifyTheIdOfTheTheEventHeBooked(String string) {
        id = string;
    }
    @When("the client requests to cancel booking of the event")
    public void theClientRequestsToCancelBookingOfTheEvent() {
        flag = app.getBookEventService().cancelBookEvent(id,app.getLoggedInUser().getUsername());
    }
    @Then("booking of the event must be cancelled")
    public void bookingOfTheEventMustBeCancelled() {
        assertTrue(flag);
    }

    @When("the client specify the id of event he will cancel {string}")
    public void theClientSpecifyTheIdOfEventHeWillCancel(String string) {
        id = string;
    }

    @Then("an error message should be displayed cancel booking {string}")
    public void anErrorMessageShouldBeDisplayedCancelBooking(String string) {
        assertEquals(app.getBookEventService().getMsg(),string);
    }

    @When("the client specify the id of event he will cancel booking {string}")
    public void theClientSpecifyTheIdOfEventHeWillCancelBooking(String string) {
        id =string;
    }


}
