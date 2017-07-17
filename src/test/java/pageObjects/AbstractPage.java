package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AbstractPage {
    protected WebDriver driver;

    public AbstractPage (WebDriver driver){
        this.driver = driver;
    }
    public LandingPage launchHotelBookingForm() {
        driver.navigate().to("http://hotel-test.equalexperts.io");
        return new LandingPage(driver);
    }

    public void waitUntilBookingIsAdded() {
        WebDriverWait wait=new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@value=' Save ']")));
    }

    public enum bookingFields {
        firstName, lastName, price, deposit, checkIn, checkOut;
    }

    public String newlyAddedBookingDetails(String fieldName) {
        String newlyAddedBookingId = driver.findElement(By.xpath("//*[@id='bookings']/*[last()]")).getAttribute("id");
        String fieldValue;

        bookingFields BookingFields = bookingFields.valueOf(fieldName);
        switch (BookingFields){
            case firstName:
                fieldValue = driver.findElement(By.xpath("//*[@id='" + newlyAddedBookingId + "']/div[1]/p")).getText();
                break;
            case lastName:
                fieldValue = driver.findElement(By.xpath("//*[@id='" + newlyAddedBookingId + "']/div[2]/p")).getText();
                break;
            case price:
                fieldValue = driver.findElement(By.xpath("//*[@id='" + newlyAddedBookingId + "']/div[3]/p")).getText();
                break;
            case deposit:
                fieldValue = driver.findElement(By.xpath("//*[@id='" + newlyAddedBookingId + "']/div[4]/p")).getText();
                break;
            case checkIn:
                fieldValue = driver.findElement(By.xpath("//*[@id='" + newlyAddedBookingId + "']/div[5]/p")).getText();
                break;
            case checkOut:
                fieldValue = driver.findElement(By.xpath("//*[@id='" + newlyAddedBookingId + "']/div[6]/p")).getText();
                break;
            default:
                throw new IllegalArgumentException("Invalid field name: " + fieldName);
            }
        return fieldValue;
    }

    public void closeDriver(){
        driver.quit();
    }
}
