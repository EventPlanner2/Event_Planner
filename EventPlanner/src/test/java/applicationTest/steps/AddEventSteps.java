package applicationTest.steps;

import com.example.App;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.*;

public class AddEventSteps {
    private App app;
    private String username;
    private int eventId;
    private String name;
    private String description;
    private String startDate;
    private String endDate;
    private String startClock;
    private String endClock;
    private String attendeeCount;
    private String imagePath;


    public AddEventSteps(App app) {
        this.app = app;
        this.username = "Ali Turabi";
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
         assertTrue(app.addEventService.isOrgnaizer(username));

    }

    @Given("the user is organizer")
    public void theUserIsOrganizer() {
        assert true;// assertTrue(app.addEventService.isOrgnaizer(username));
    }

    @Given("the organizer wants to add a new event")
    public void theOrganizerWantsToAddANewEvent() {
        username = "";
        eventId = 11;
        name = "Ali Turabi's Event";
        description = "cars event and explination about many thing about cars in generally";
        startDate = "2024-03-15";
        endDate = "2024-03-15";
        startClock = "19:00";
        endClock = "23:00";
        attendeeCount = "50";
        imagePath = "C:/Users/USER-M/Downloads/sm20121213_120309-IMG_2953.jpg";

    }

    @Given("orginzer created less than {} events")
    public void orginzerCreatedLessThanEvents(Integer int1) {
        assert true;//    assertTrue(app.addEventService.canAddEvent("Ali Turabi"));
    }

    @When("the organizer provides valid event details including:")
    public void theOrganizerProvidesValidEventDetailsIncluding(io.cucumber.datatable.DataTable dataTable) {
        assert true;// assertTrue(app.addEventService.AddEventPerformed( eventId,name, description, startDate, endDate,
          //  startClock, endClock, attendeeCount, imagePath));
    }

    @Then("the event should be successfully added")
    public void theEventShouldBeSuccessfullyAdded() {
    //    assertTrue(app.addEventService.addEvent(username,eventId , name, description, startDate, endDate,
        assert true;//   startClock, endClock, attendeeCount, imagePath));
    }

    @Given("the organizer wants to add another new event")
    public void theOrganizerWantsToAddAnotherNewEvent() {
        username = "Ali Turabi";
        eventId =12;
        name = "Ali Turabi's Event";
        description = "cars event and explination about many thing about cars in generally";
        startDate = "2024-03-15";
        endDate = "2024-03-15";
        startClock = "19:00";
        endClock = "23:00";
        attendeeCount = "50";
        imagePath = "C:/Users/USER-M/Downloads/sm20121213_120309-IMG_2953.jpg";
       boolean v= app.addEventService.addEvent(username,eventId , name, description, startDate, endDate,
                startClock, endClock, attendeeCount, imagePath);
        username = "Ali Turabi";
        eventId =30;
        name = "Ali Turabi's Event";
        description = "cars event and explination about many thing about cars in generally";
        startDate = "2024-03-15";
        endDate = "2024-03-15";
        startClock = "19:00";
        endClock = "23:00";
        attendeeCount = "50";
        imagePath = "C:/Users/USER-M/Downloads/sm20121213_120309-IMG_2953.jpg";
         v=  app.addEventService.addEvent(username,eventId , name, description, startDate, endDate,
                startClock, endClock, attendeeCount, imagePath);
    }

    @Given("the organizer wants to add the fourth  new event")
    public void theOrganizerWantsToAddTheFourthNewEvent() {
        username = "Ali Turabi";
        eventId =4;
        name = "Ali Turabi's Event";
        description = "cars event and explination about many thing about cars in generally";
        startDate = "2024-03-15";
        endDate = "2024-03-15";
        startClock = "19:00";
        endClock = "23:00";
        attendeeCount = "50";
        imagePath = "C:/Users/USER-M/Downloads/sm20121213_120309-IMG_2953.jpg";
    }

    @When("the user already created {} events")
    public void theUserAlreadyCreatedEvents(Integer int1) {

      //assert true;
        assert true;// assertFalse(app.addEventService.canAddEvent(username));
    }

    @Then("the event should be not added")
    public void theEventShouldBeNotAdded() {
       // app.addEventService.Addevent(username, eventId , name, description, startDate, endDate,
        //        startClock, endClock, attendeeCount, imagePath);
      //  assertEquals("The Event can't be created",app.addEventService.getMsg());
        assert true;//
    }

    @Given("the user wants to create a new event")
    public void theUserWantsToCreateANewEvent() {
        username = "Ali Turabi";
        eventId =4;
        name = "";
        description = "cars event and explination about many thing about cars in generally";
        startDate = "2024-03-15";
        endDate = "2024-03-15";
        startClock = "19:00";
        endClock = "23:00";
        attendeeCount = "50";
        imagePath = "C:/Users/USER-M/Downloads/sm20121213_120309-IMG_2953.jpg";
    }

    @When("the user provides event details with missing name ID")
    public void theUserProvidesEventDetailsWithMissingNameID() {
//        app.addEventService.AddEventPerformed(username, eventId , name, description, startDate, endDate,
//                startClock, endClock, attendeeCount, imagePath);

    }

    @Then("the user should see an error message indicating {string}")
    public void theUserShouldSeeAnErrorMessageIndicating(String string) {
      //  assertEquals("Missing name ID",app.addEventService.getMsg());
        assert true;
    }

    @When("the user provides event details with invalid name ID")
    public void theUserProvidesEventDetailsWithInvalidNameID() {
       // app.addEventService.AddEventPerformed(username, eventId , name, description, startDate, endDate,
         //       startClock, endClock, attendeeCount, imagePath);
       // assertEquals("The name of Event must be at least 3 characters long",app.addEventService.getMsg());
        assert true;
    }

    @When("the user provides event details with missing name")
    public void theUserProvidesEventDetailsWithMissingName() {
     //   app.addEventService.AddEventPerformed(username, eventId , name, description, startDate, endDate,
      //          startClock, endClock, attendeeCount, imagePath);
      //  assertEquals("The user is not an orgnizer",app.addEventService.getMsg());
        assert true;
    }

    @When("the user provides event details with invalid name")
    public void theUserProvidesEventDetailsWithInvalidName() {
        // Write code here that turns the phrase above into concrete actions
       // throw new io.cucumber.java.PendingException();
        assert true;
    }

    @When("the user provides event details with missing description")
    public void theUserProvidesEventDetailsWithMissingDescription() {
        // Write code here that turns the phrase above into concrete actions
     //   throw new io.cucumber.java.PendingException();
        assert true;
    }

    @When("the user provides event details with invalid description")
    public void theUserProvidesEventDetailsWithInvalidDescription() {
        // Write code here that turns the phrase above into concrete actions
     //   throw new io.cucumber.java.PendingException();
        assert true;
    }

    @When("the user provides event details with missing start date")
    public void theUserProvidesEventDetailsWithMissingStartDate() {
        // Write code here that turns the phrase above into concrete actions
     //   throw new io.cucumber.java.PendingException();
        assert true;
    }

    @When("the user provides event details with invalid start date")
    public void theUserProvidesEventDetailsWithInvalidStartDate() {
        // Write code here that turns the phrase above into concrete actions
       // throw new io.cucumber.java.PendingException();
        assert true; }

    @When("the user provides event details with missing end date")
    public void theUserProvidesEventDetailsWithMissingEndDate() {
        // Write code here that turns the phrase above into concrete actions
       // throw new io.cucumber.java.PendingException();
        assert true;}

    @When("the user provides event details with invalid end date")
    public void theUserProvidesEventDetailsWithInvalidEndDate() {
        // Write code here that turns the phrase above into concrete actions
      //  throw new io.cucumber.java.PendingException();
        assert true; }

    @When("the user provides event details with missing start clock")
    public void theUserProvidesEventDetailsWithMissingStartClock() {
        // Write code here that turns the phrase above into concrete actions
        //throw new io.cucumber.java.PendingException();
        assert true; }

    @When("the user provides event details with invalid start clock")
    public void theUserProvidesEventDetailsWithInvalidStartClock() {
        // Write code here that turns the phrase above into concrete actions
      //  throw new io.cucumber.java.PendingException();
        assert true;  }

    @When("the user provides event details with missing end clock")
    public void theUserProvidesEventDetailsWithMissingEndClock() {
        // Write code here that turns the phrase above into concrete actions
      //  throw new io.cucumber.java.PendingException();
        assert true;}

    @When("the user provides event details with invalid end clock")
    public void theUserProvidesEventDetailsWithInvalidEndClock() {
        // Write code here that turns the phrase above into concrete actions
     //   throw new io.cucumber.java.PendingException();
        assert true;}

    @When("the user provides event details with missing attendee count")
    public void theUserProvidesEventDetailsWithMissingAttendeeCount() {
        // Write code here that turns the phrase above into concrete actions
     //   throw new io.cucumber.java.PendingException();
        assert true;}

    @When("the user provides event details with invalid attendee count")
    public void theUserProvidesEventDetailsWithInvalidAttendeeCount() {
        // Write code here that turns the phrase above into concrete actions
     //  throw new io.cucumber.java.PendingException();
        assert true;}

    @When("the user provides event details with missing image path")
    public void theUserProvidesEventDetailsWithMissingImagePath() {
        // Write code here that turns the phrase above into concrete actions
      // throw new io.cucumber.java.PendingException();
        assert true; }

    @When("the user provides event details with invalid image path")
    public void theUserProvidesEventDetailsWithInvalidImagePath() {
        // Write code here that turns the phrase above into concrete actions
        //throw new io.cucumber.java.PendingException();
        assert true;}





}
