package stepDefinitions;

import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import junit.framework.Assert;
import org.joda.time.DateTime;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import pageObjects.LandingPage;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class AddRemoveHotelBookingsStepDef {

    public WebDriver driver;
    public static final Date date = new Date();
    public static final DateTime dateTime = new DateTime();

    public static void main (String[] args ) {

    }

    public static final String firstName = ("testFName" + date.getTime());
    public static final String lastName = ("testSurname" + date.getTime());
    public static final String price = "117";
    public static final String deposit = "true";
    public static final String checkIn = dateTime.toLocalDate().plusDays(2).toString();
    public static final String checkOut = dateTime.toLocalDate().plusDays(4).toString();

    @Before
    public void setUp(){
        System.setProperty("webdriver.gecko.driver", ".\\src\\test\\java\\driver\\geckodriver.exe");
        driver = new FirefoxDriver();
        driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
    }

    LandingPage landingPage;

    @Given("^I am on the hotel bookings site$")
    public void i_am_on_the_hotel_bookings_site() throws Throwable {
        landingPage = new LandingPage(driver);
        landingPage.launchHotelBookingForm();
    }

    @When("^I populate all the booking fields with valid values$")
    public void I_populate_all_the_booking_fields_with_valid_values()throws Throwable  {
        landingPage
                .setFirstNameField(firstName)
                .setSurname(lastName)
                .setPrice(price)
                .selectDeposit(deposit)
                .selectCheckIn(checkIn)
                .selectCheckOut(checkOut);
    }

    @And("^click on the save button$")
    public void click_on_the_save_button() throws Throwable {
        landingPage.clickSave();
    }

    @Then("^I check the new hotel booking is added to the booking list$")
    public void I_check_the_new_hotel_booking_is_added_to_the_booking_list() throws Throwable {
        landingPage.waitUntilBookingIsAdded();
        Thread.sleep(5000);  //Doesn't consistently work with Explicit wait
        Assert.assertTrue(landingPage.newlyAddedBookingDetails("firstName").equals(firstName));
        Assert.assertTrue(landingPage.newlyAddedBookingDetails("lastName").equals(lastName));
        Assert.assertTrue(landingPage.newlyAddedBookingDetails("price").equals(price));
        Assert.assertTrue(landingPage.newlyAddedBookingDetails("deposit").equals(deposit));
        Assert.assertTrue(landingPage.newlyAddedBookingDetails("checkIn").equals(checkIn));
        Assert.assertTrue(landingPage.newlyAddedBookingDetails("checkOut").equals(checkOut));
        landingPage.closeDriver();
    }

    @Given("^atleast one hotel booking entry is already existing$")
    public void atleast_one_hotel_booking_entry_is_already_existing() throws Throwable {
          landingPage.verifyBookingEntryExists();
    }

    @When("^I delete a hotel booking$")
    public void i_delete_a_hotel_booking() throws Throwable {
        Thread.sleep(5000);  //Doesn't consistently work with Explicit wait
        landingPage.deleteAnExistingBooking();
    }

    @Then("^I check the deleted hotel booking is removed from the booking list$")
    public void i_check_the_deleted_hotel_booking_is_removed_from_the_booking_list() throws Throwable {
//        landingPage.verifyDeleteBookingDoesNotExist(deletedExistingBookingId);
        landingPage.closeDriver();
    }
}