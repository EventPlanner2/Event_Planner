package applicationTest.steps;

import com.example.App;
import com.example.data.RoomData;
import com.example.entites.Event;
import com.example.entites.Room;
import com.example.entites.ServiceProvider;
import com.example.entites.User;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;

import java.util.ArrayList;
import java.util.Objects;

import static org.junit.Assert.*;

public class ReserveSPSteps {


    App app;
    boolean flag;
    public ReserveSPSteps(App app){
        this.app = app;
        flag = false;
    }
    @When("the organizer selects to reserve a service provider")
    public void theOrganizerSelectsToReserveAServiceProvider() {
        app.getReserveSPService ().setLoggedInUser( app.getLoggedInUser () );
        app.getReserveSPService ().ChooseReserveSP();
    }
    @Then("the system should display a list of events related to him")
    public void theSystemShouldDisplayAListOfEventsRelatedToHim() {

        ArrayList <Event> arr = app.getReserveSPService ().res_event;
        for(Event e : arr){
            if(!e.getUsername().equals( app.getLoggedInUser ().getUsername()))
                fail();
        }

    }
    @Then("the system should list all registered service providers with complete account")
    public void theSystemShouldListAllRegisteredServiceProviders() {
        ArrayList <ServiceProvider> arr = app.getReserveSPService ().res_sps;
        for(ServiceProvider sp : arr){
            if(sp.isFirstLogin())
                fail();
        }
    }

    @When("the organizer selects EventID {string} and ProviderName {string}")
    public void theOrganizerSelectsEventIDAndProviderName(String string, String string2) {
        flag = app.getReserveSPService ().ReserveSPPerform(string,string2);
    }
    @Then("the service provider is reserved to event")
    public void theServiceProviderIsReservedToEvent() {
        assertTrue(flag);
    }


}
