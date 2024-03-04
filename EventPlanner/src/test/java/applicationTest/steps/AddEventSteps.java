package applicationTest.steps;

import com.example.App;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

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
    private boolean result;
    private int oldSize;

    public AddEventSteps(App app) {
        this.app = app;
        this.username = "";
        this.eventId = 0;
        this.name = "";
        this.description = "";
        this.startDate = "";
        this.endDate = "";
        this.startClock = "";
        this.endClock = "";
        this.attendeeCount = "";
        this.imagePath = "";
        this.result = false;
        this.oldSize = 0;
    }






    @Given("the user is logged in with their account")
    public void theUserIsLoggedInWithTheirAccount() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Given("the user is organizer")
    public void theUserIsOrganizer() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Given("the organizer wants to add a new event")
    public void theOrganizerWantsToAddANewEvent() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Given("orginzer created less than {int} events")
    public void orginzerCreatedLessThanEvents(Integer int1) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @When("the organizer provides valid event details including:")
    public void theOrganizerProvidesValidEventDetailsIncluding(io.cucumber.datatable.DataTable dataTable) {
        // Write code here that turns the phrase above into concrete actions
        // For automatic transformation, change DataTable to one of
        // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
        // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
        // Double, Byte, Short, Long, BigInteger or BigDecimal.
        //
        // For other transformations you can register a DataTableType.
        throw new io.cucumber.java.PendingException();
    }

    @Then("the event should be successfully added")
    public void theEventShouldBeSuccessfullyAdded() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Given("the organizer wants to add another new event")
    public void theOrganizerWantsToAddAnotherNewEvent() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Given("the organizer wants to add the fourth  new event")
    public void theOrganizerWantsToAddTheFourthNewEvent() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @When("the user already created {int} events")
    public void theUserAlreadyCreatedEvents(Integer int1) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Then("the event should be not added")
    public void theEventShouldBeNotAdded() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Given("the user wants to create a new event")
    public void theUserWantsToCreateANewEvent() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @When("the user provides event details with missing name ID")
    public void theUserProvidesEventDetailsWithMissingNameID() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Then("the user should see an error message indicating {string}")
    public void theUserShouldSeeAnErrorMessageIndicating(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @When("the user provides event details with invalid name ID")
    public void theUserProvidesEventDetailsWithInvalidNameID() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @When("the user provides event details with missing username")
    public void theUserProvidesEventDetailsWithMissingUsername() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @When("the user provides event details with invalid username")
    public void theUserProvidesEventDetailsWithInvalidUsername() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @When("the user provides event details with missing name")
    public void theUserProvidesEventDetailsWithMissingName() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @When("the user provides event details with invalid name")
    public void theUserProvidesEventDetailsWithInvalidName() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @When("the user provides event details with missing description")
    public void theUserProvidesEventDetailsWithMissingDescription() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @When("the user provides event details with invalid description")
    public void theUserProvidesEventDetailsWithInvalidDescription() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @When("the user provides event details with missing start date")
    public void theUserProvidesEventDetailsWithMissingStartDate() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @When("the user provides event details with invalid start date")
    public void theUserProvidesEventDetailsWithInvalidStartDate() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @When("the user provides event details with missing end date")
    public void theUserProvidesEventDetailsWithMissingEndDate() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @When("the user provides event details with invalid end date")
    public void theUserProvidesEventDetailsWithInvalidEndDate() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @When("the user provides event details with missing start clock")
    public void theUserProvidesEventDetailsWithMissingStartClock() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @When("the user provides event details with invalid start clock")
    public void theUserProvidesEventDetailsWithInvalidStartClock() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @When("the user provides event details with missing end clock")
    public void theUserProvidesEventDetailsWithMissingEndClock() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @When("the user provides event details with invalid end clock")
    public void theUserProvidesEventDetailsWithInvalidEndClock() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @When("the user provides event details with missing attendee count")
    public void theUserProvidesEventDetailsWithMissingAttendeeCount() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @When("the user provides event details with invalid attendee count")
    public void theUserProvidesEventDetailsWithInvalidAttendeeCount() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @When("the user provides event details with missing image path")
    public void theUserProvidesEventDetailsWithMissingImagePath() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @When("the user provides event details with invalid image path")
    public void theUserProvidesEventDetailsWithInvalidImagePath() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }





}
