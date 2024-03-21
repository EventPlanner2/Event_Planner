package applicationTest.steps;

import com.example.App;
import com.example.entites.Client;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.*;

public class AddEventSteps {
    private App app;
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
    private boolean isCreated;


    public AddEventSteps(App app) {
        this.app = app;
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

    @Given("the user is logged in with their account")
    public void theUserIsLoggedInWithTheirAccount()
    {
         app.setLoggedInUser ( app.getLoginService ().LoginPerformed ( username, password ) );

    }

    @Given("the user is organizer")
    public void theUserIsOrganizer() {
        assertTrue( app.getAddEventService ().isOrgnaizer(username));
    }

    @Given("the organizer wants to add a new event")
    public void theOrganizerWantsToAddANewEvent() {
        app.getAddEventService ().AddingEvent = true;

    }

    @Given("orginzer created less than {} events")
    public void orginzerCreatedLessThanEvents(Integer int1) {

        assertTrue( app.getAddEventService ().canAddEvent(username));
    }

    @When("the organizer provides valid event details including:")
    public void theOrganizerProvidesValidEventDetailsIncluding(io.cucumber.datatable.DataTable dataTable) {
        username = dataTable.cell(0,1);
        eventId = Integer.parseInt(dataTable.cell(1,1));
        name = dataTable.cell(2,1);
        description = dataTable.cell(3,1);
        startDate = dataTable.cell(4,1);
        endDate = dataTable.cell(5,1);
        startClock = dataTable.cell(6,1);
        endClock = dataTable.cell(7,1);
        attendeeCount = dataTable.cell(8,1);
        imagePath = dataTable.cell(9,1);
        isCreated=  app.getAddEventService ().addEvent(username,eventId , name, description, startDate, endDate,
                startClock, endClock, attendeeCount, imagePath);
    }

    @Then("the event should be successfully added")
    public void theEventShouldBeSuccessfullyAdded() {
        assertEquals( app.getAddEventService ().getMsg(),"The Event is created");
        assertTrue(isCreated);
    }

    @Given("the organizer wants to add another new event")
    public void theOrganizerWantsToAddAnotherNewEvent() {
        app.getAddEventService ().AddingEvent = true;

    }

    @Given("the organizer wants to add the fourth  new event")
    public void theOrganizerWantsToAddTheFourthNewEvent() {
        app.getAddEventService ().AddingEvent = true;
    }

    @When("the user already created {} events")
    public void theUserAlreadyCreatedEvents(int int1) {
        assertEquals(Client.getClientFromData(username).getNumberEvent(),int1);
    }

    @When("the user choose to add event")
    public void theUserChooseToAddEvent(){

        isCreated=  app.getAddEventService ().addEvent(username,eventId , name, description, startDate, endDate,
                startClock, endClock, attendeeCount, imagePath);
    }
    @Then("the event should be not added")
    public void theEventShouldBeNotAdded() {
        assertFalse(isCreated);
    }

    @Given("the user wants to create a new event")
    public void theUserWantsToCreateANewEvent() {
        username = "Ali Turabi";
        eventId =4;
        name = "Ali Turabi's Event";
        description = "cars event and explination about many thing about cars in generally";
        startDate = "2024-03-15";
        endDate = "2024-03-15";
        startClock = "19:00";
        endClock = "23:00";
        attendeeCount = "50";
        imagePath = "resources\\images\\57.png";

    }


    @Then("the user should see an error message indicating {string}")
    public void theUserShouldSeeAnErrorMessageIndicating(String string) {
        assertEquals( app.getAddEventService ().getMsg(),string);
    }


    @When("the user provides event details with missing name")
    public void theUserProvidesEventDetailsWithMissingName() {
        name = "";
    }

    @When("the user provides event details with invalid name")
    public void theUserProvidesEventDetailsWithInvalidName() {
        name ="a";
    }

    @When("the user provides event details with missing description")
    public void theUserProvidesEventDetailsWithMissingDescription() {
        description ="";
    }

    @When("the user provides event details with invalid description")
    public void theUserProvidesEventDetailsWithInvalidDescription() {
        description = "abasd";
    }

    @When("the user provides event details with missing start date")
    public void theUserProvidesEventDetailsWithMissingStartDate() {
        startDate = "";
    }

    @When("the user provides event details with invalid start date")
    public void theUserProvidesEventDetailsWithInvalidStartDate() {
        startDate = "qf";
    }

    @When("the user provides event details with missing end date")
    public void theUserProvidesEventDetailsWithMissingEndDate() {
        endDate = "";
    }

    @When("the user provides event details with invalid end date")
    public void theUserProvidesEventDetailsWithInvalidEndDate() {
        endDate ="ab";
    }

    @When("the user provides event details with missing start clock")
    public void theUserProvidesEventDetailsWithMissingStartClock() {
        startClock = "";
    }

    @When("the user provides event details with invalid start clock")
    public void theUserProvidesEventDetailsWithInvalidStartClock() {
        startClock = "bc";
    }

    @When("the user provides event details with missing end clock")
    public void theUserProvidesEventDetailsWithMissingEndClock() {
        endClock = "";
    }

    @When("the user provides event details with invalid end clock")
    public void theUserProvidesEventDetailsWithInvalidEndClock() {
       endClock = "oo";
    }

    @When("the user provides event details with missing attendee count")
    public void theUserProvidesEventDetailsWithMissingAttendeeCount() {
        attendeeCount = "";
    }

    @When("the user provides event details with invalid attendee count")
    public void theUserProvidesEventDetailsWithInvalidAttendeeCount() {
        attendeeCount = "op";
    }

    @When("the user provides event details with missing image path")
    public void theUserProvidesEventDetailsWithMissingImagePath() {
        imagePath = "";
    }

    @When("the user provides event details with invalid image path")
    public void theUserProvidesEventDetailsWithInvalidImagePath() {
        imagePath = "asdasd";
    }





}
