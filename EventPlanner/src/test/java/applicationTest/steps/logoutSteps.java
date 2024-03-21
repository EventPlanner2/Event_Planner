package applicationTest.steps;

import com.example.App;
import com.example.entites.User;
import io.cucumber.java.en.*;
import static org.junit.Assert.*;
public class logoutSteps {

    App app;
    private String msg;
    public logoutSteps(App app){
        this.app = app;
        msg = "";
    }

    @Given("the user is logged in")
    public void the_user_is_logged_in() {
        app.setLoggedInUser ( app.getLoginService ().LoginPerformed ( "Admin", "123456" ) );
        assertNotNull( app.getLoggedInUser () );
    }
    @When("the user selects the option to log out")
    public void the_user_selects_the_option_to_log_out() {
        msg = app.logoutPerform();
    }
    @Then("the user should be successfully logged out and show message {string}")
    public void the_user_is_redirected_to_the_login_page(String string) {
        assertEquals(msg,string);
    }

    @When("the user selects the option to log out and encounters a logout issue happened")
    public void the_user_selects_the_option_to_log_out_and_encounters_an_issue() {
        app.setLoggedInUser ( null );
        msg = app.logoutPerform();

    }
    @Then("the system should display a warning message about the logout failure {string}")
    public void the_system_should_display_a_warning_message_about_the_logout_failure(String string) {

        assertEquals(msg,string);

    }

}
