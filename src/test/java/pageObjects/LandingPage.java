package pageObjects;

import junit.framework.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class LandingPage extends AbstractPage {

    public LandingPage (WebDriver driver) {
        super(driver);
    }

    public LandingPage setFirstNameField(String value){
        driver.findElement(By.id("firstname")).sendKeys(value);
        return new LandingPage(driver);
    }

    public LandingPage setSurname(String value){
        driver.findElement(By.id("lastname")).sendKeys(value);
        return new LandingPage(driver);
    }

    public LandingPage setPrice(String value){
        driver.findElement(By.id("totalprice")).sendKeys(value);
        return new LandingPage(driver);
    }

    public LandingPage selectDeposit(String dropdownItem) {
        Select dropdown = new Select(driver.findElement(By.id("depositpaid")));
        dropdown.selectByVisibleText(dropdownItem);
        return new LandingPage(driver);
    }

    public LandingPage selectCheckIn(String date) {
        driver.findElement(By.id("checkin")).sendKeys(date);
        return new LandingPage(driver);
    }

    public LandingPage selectCheckOut(String date) {
        driver.findElement(By.id("checkout")).sendKeys(date);
        return new LandingPage(driver);
    }

    public LandingPage clickSave() {
        driver.findElement(By.xpath("//*[@value=' Save ']")).click();
        return new LandingPage(driver);
    }

    public LandingPage verifyBookingEntryExists() {
        List<WebElement> rows = driver.findElements(By.xpath("//*[@id='bookings'] //*[@class='row']"));
        Assert.assertTrue(rows.size() > 0);
        return new LandingPage(driver);
    }
    public boolean deleteAnExistingBooking() {
        String deletedExistingBookingId = driver.findElement(By.xpath("//*[@value='Delete']")).getAttribute("onClick");
        deletedExistingBookingId = deletedExistingBookingId.replace("deleteBooking(","");
        deletedExistingBookingId = deletedExistingBookingId.replace(")","");
        driver.findElement(By.xpath("//*[@value='Delete']")).click();
        List<WebElement> rows = driver.findElements(By.id("//*[@id='" + deletedExistingBookingId + "']"));
        return rows.size() > 0 ? false : true;
    }

    public LandingPage verifyDeleteBookingDoesNotExist(String deletedExistingBookingId) {
        List<WebElement> rows = driver.findElements(By.id("//*[@id='" + deletedExistingBookingId + "']"));
        return new LandingPage(driver);
    }

}
