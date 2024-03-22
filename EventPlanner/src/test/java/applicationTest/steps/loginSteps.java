package applicationTest.steps;

import com.example.App;
import com.example.entites.User;
import io.cucumber.java.en.*;
import static org.junit.Assert.*;


public class loginSteps {

    App app;
    private String username;
    private String password;

    // dependency injection
    public loginSteps(App app){
        this.app = app;
        username = "";
        password = "";
    }
    @Given("the user has not logged in yet")
    public void the_user_has_not_logged_in_yet() {
        assertNull( app.getLoggedInUser () );
    }
    @Given("the user has a valid username {string} and password {string}")
    public void the_user_has_a_valid_username_and_password(String validUsername, String validPass) {
        username = validUsername;
        password = validPass;
    }
    @When("the user logs in with valid credentials")
    public void the_user_logs_in_with_valid_credentials() {

        app.setLoggedInUser ( app.getLoginService ().LoginPerformed ( username, password ) );
    }
    @Then("the user should be logged in successfully")
    public void the_user_should_be_logged_in_successfully() {
        assertNotNull( app.getLoggedInUser () );
    }

    @Given("the user has a valid username {string} and incorrect password {string}")
    public void the_user_has_a_valid_username_and_incorrect_password(String validUsername , String invalidPass) {
        username = validUsername;
        password = invalidPass;
    }
    @When("the user logs in with invalid password")
    public void the_user_logs_in_with_invalid_password() {
        app.setLoggedInUser ( app.getLoginService ().LoginPerformed ( username, password ) );
        assertNull( app.getLoggedInUser () );
    }

    @Given("the user has an invalid username {string} and valid password {string}")
    public void the_user_has_an_invalid_username_and_valid_password(String invalidUsername,String validPass) {
        username = invalidUsername;
        password = validPass;
    }
    @When("the user logs in with invalid username")
    public void the_user_logs_in_with_invalid_username() {
        app.setLoggedInUser ( app.getLoginService ().LoginPerformed ( username, password ) );
        assertNull( app.getLoggedInUser () );
    }


    @Given("the user has an invalid username {string} and incorrect password {string}")
    public void the_user_has_an_invalid_username_and_incorrect_password(String invalidUsername , String invalidPass) {
        username = invalidUsername;
        password = invalidPass;
    }
    @When("the user logs in with both invalid username and password")
    public void the_user_logs_in_with_both_invalid_username_and_password() {
        app.setLoggedInUser ( app.getLoginService ().LoginPerformed ( username, password ) );
        assertNull( app.getLoggedInUser () );
    }

    @Given("the user has empty username and password")
    public void the_user_has_empty_username_and_password() {
        username = "";
        password = "";
    }
    @When("the user tries to log in with empty credentials")
    public void the_user_tries_to_log_in_with_empty_credentials() {
        app.setLoggedInUser ( app.getLoginService ().LoginPerformed ( username, password ) );
        assertNull( app.getLoggedInUser () );
    }
    @Then("the user should see an error message {string}")
    public void the_user_should_see_an_error_message(String msg) {
        assertEquals( app.getLoginService ().getErrorMessage (),msg);
    }
}
