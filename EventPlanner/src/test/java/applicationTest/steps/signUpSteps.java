package applicationTest.steps;

import com.example.App;
import com.example.entites.User;
import io.cucumber.java.en.*;
import static org.junit.Assert.*;
public class signUpSteps {

    App app;
    private String username;
    private String password;
    private String email;
    private String role;
    private boolean flag_is_created;

    // Dependency Injection
    public signUpSteps(App app){
        this.app = app;
        this.email = "";
        this.role = "";
        this.username = "";
        this.password = "";
    }
    @Given("the user is on the SignUp page")
    public void the_user_is_on_the_sign_up_page() {
        app.setCurrentPage ( "SignUp" );
    }
    @When("the user provides available username {string}")
    public void the_user_provides_available_username(String string) {
        username = string;
    }
    @When("the user provides valid email format {string}")
    public void the_user_provides_valid_email_format(String string) {
        email = string;
    }

    @When("the user provides valid role {string}")
    public void userProvidesValidRole(String string) {
        role = string;
    }
    @When("the user provides strong password {string}")
    public void the_user_provides_strong_password(String string) {
        password = string;
    }


    @When("hit the register button")
    public void hit_the_register_button() {
        flag_is_created = app.getSignUpService ().register(username,password,email,role);
    }



    @Then("the user account is created successfully")
    public void the_user_account_is_created_successfully() {
        assertTrue(flag_is_created);
    }


    @Given("an existing user with email {string}")
    public void an_existing_user_with_email(String string) {
        assertTrue( app.getSignUpService ().email_existed(string));
    }
    @When("the user provides a contact email {string} that is already registered")
    public void the_user_provides_a_contact_email_that_is_already_registered(String string) {
        email = string;
        flag_is_created = app.getSignUpService ().register(username,password,email,role);
    }
    @Then("the system displays an existing contact email error message {string}")
    public void the_system_displays_an_existing_contact_email_error_message(String string) {

        assertFalse(flag_is_created);
        assertEquals( app.getSignUpService ().msg,string);

    }



    @When("the user provides an invalid contact email format {string}")
    public void the_user_provides_an_invalid_contact_email_format(String string) {
        email=string;
        flag_is_created = app.getSignUpService ().register(username,password,email,role);
    }
    @Then("the system displays an invalid email format error message {string}")
    public void the_system_displays_an_invalid_email_format_error_message(String string) {
        assertFalse(flag_is_created);
        assertEquals( app.getSignUpService ().msg,string);
    }

    @When("the user submits the SignUp form with missing email")
    public void theUserSubmitsTheSignUpFormWithMissingEmail() {
        email = "";
        flag_is_created = app.getSignUpService ().register(username,password,email,role);
    }

    @When("the user submits the SignUp form with missing username")
    public void theUserSubmitsTheSignUpFormWithMissingUsername() {
        username = "";
        flag_is_created = app.getSignUpService ().register(username,password,email,role);
    }

    @And("the user submits the SignUp form with missing password")
    public void theUserSubmitsTheSignUpFormWithMissingPassword() {
        password = "";
        flag_is_created = app.getSignUpService ().register(username,password,email,role);
    }
    @Given("an existing user with username {string}")
    public void an_existing_user_with_username(String string) {
        assertTrue( app.getSignUpService ().username_existed(string));
    }
    @When("the user provides the existing username {string}")
    public void the_user_provides_the_existing_username(String string) {
        username = string;
        flag_is_created = app.getSignUpService ().register(username,password,email,role);
    }
    @Then("the system displays an error message for the existing username {string}")
    public void the_system_displays_an_error_message_for_the_existing_username(String string) {
        assertFalse(flag_is_created);
        assertEquals( app.getSignUpService ().msg,string);
    }




    @Then("the system displays error messages for the missing fields {string}")
    public void the_system_displays_error_messages_for_the_missing_fields(String string) {
        assertFalse(flag_is_created);
        assertEquals( app.getSignUpService ().msg,string);
    }




    @When("the user provides a password that is too short {string}")
    public void the_user_provides_a_password_that_is_too_short(String string) {
        password = string;
        flag_is_created = app.getSignUpService ().register(username,password,email,role);
    }
    @Then("the system displays a weak password error message says {string}")
    public void the_system_displays_a_weak_password_error_message_says(String string) {
        assertFalse(flag_is_created);
        assertEquals( app.getSignUpService ().msg,string);
    }




    @When("the user provides an invalid role {string}")
    public void the_user_provides_an_invalid_role(String string) {
        role = string;
        flag_is_created = app.getSignUpService ().register(username,password,email,role);
    }
    @Then("the system displays an error message for the invalid role {string}")
    public void the_system_displays_an_error_message_for_the_invalid_role(String string) {
        assertFalse(flag_is_created);
        assertEquals( app.getSignUpService ().msg,string);
    }



}
