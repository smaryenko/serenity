package tests.api.booking;

import models.CreateItemsHelper;
import models.booking.Booking;
import net.andreinc.mockneat.MockNeat;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.WithTag;
import org.apache.http.HttpStatus;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import steps.api.BookingSteps;
import tests.api.TestBaseApi;

import java.time.LocalDate;

@RunWith(SerenityRunner.class)
@WithTag(type="api", name="Booking")
public class BookingTests extends TestBaseApi {
    @Steps
    BookingSteps bookingSteps;

    private Booking bookingForTest;

    @Before
    public void before() {
        MockNeat mock = MockNeat.threadLocal();
        bookingForTest = CreateItemsHelper.createNewBooking(
                mock.ints().range(10000, 999999).val(),
                LocalDate.now(),
                LocalDate.now().plusDays(1));
    }

    @Test
    public void testPostNewBooking() {
        //Arrange

        //Act
        Booking createdBooking = bookingSteps
                .makePostNewBookingCall(bookingForTest)
                .assertResponseCode(HttpStatus.SC_CREATED)
                .extractCreatedBooking();

        //Assert
        bookingSteps.checkBookingsAreEqual(bookingForTest, createdBooking);
    }

    @Test
    public void testPostOnSameDates() {
        //Arrange

        //Act
        bookingSteps
                .makePostNewBookingCall(bookingForTest)
                .assertResponseCode(HttpStatus.SC_CREATED)
                .makePostNewBookingCall(bookingForTest)

                //Assert
                .assertResponseCode(HttpStatus.SC_CONFLICT);
    }

    @Test
    public void testPostInvalidDates() {
        //Arrange
        bookingForTest.getBookingdates().setCheckout(bookingForTest.getBookingdates().getCheckin().minusDays(1));

        //Act
        bookingSteps
                .makePostNewBookingCall(bookingForTest)

        //Assert
                .assertResponseCode(HttpStatus.SC_CONFLICT);
    }

    @Test
    public void testGetNewBooking() {
        //Arrange
        Booking createdBooking = bookingSteps
                .makePostNewBookingCall(bookingForTest)
                .assertResponseCode(HttpStatus.SC_CREATED)
                .extractCreatedBooking();

        //Act
        Booking getBooking = bookingSteps
                .makeGetBookingCall(createdBooking.getBookingid())
                .assertResponseCode(HttpStatus.SC_OK)
                .extractBooking();

        //Assert
        bookingSteps.checkBookingsAreEqual(bookingForTest, getBooking);
    }

    @Test
    public void testGetAllBookings() {
        //Arrange
        bookingSteps
                .makePostNewBookingCall(bookingForTest)
                .assertResponseCode(HttpStatus.SC_CREATED);

        //Act
        Booking[] getBookings = bookingSteps
                .makeGetAllBookingsCall(null)
                .assertResponseCode(HttpStatus.SC_OK)
                .extractBookings();

        //Assert
        Assert.assertTrue(getBookings.length >= 2);
    }

    //Will fail because delete requires token from somewhere
    @Test()
    public void testDeleteBooking() {
        //Arrange
        Booking createdBooking = bookingSteps
                .makePostNewBookingCall(bookingForTest)
                .assertResponseCode(HttpStatus.SC_CREATED)
                .extractCreatedBooking();

        //Act
        bookingSteps
                .makeDeleteBookingCall(createdBooking.getBookingid())
                .assertResponseCode(HttpStatus.SC_OK)
                .makeGetBookingCall(createdBooking.getBookingid())

        //Assert
                .assertResponseCode(HttpStatus.SC_NOT_FOUND);
    }
}
