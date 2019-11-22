package steps.api;

import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import models.booking.Booking;
import models.booking.CreatedBooking;
import net.thucydides.core.annotations.Step;
import org.assertj.core.api.SoftAssertions;
import tests.api.TestBaseApi;
import utils.properties.PropertiesSupplier;
import utils.restassured.RestAssuredHelper;

import static net.serenitybdd.rest.SerenityRest.then;
import static org.hamcrest.Matchers.is;

public class BookingSteps {

    private static final String BASE_PATH = "booking";
    private static RequestSpecification ra = RestAssuredHelper.createSerenityRest(TestBaseApi.baseUrl);

    public BookingSteps() {
        ra.basePath(BASE_PATH);
    }

    @Step("Check whether status code is as expected")
    public BookingSteps assertResponseCode(int statusCode) {
        then().statusCode(is(statusCode));
        return this;
    }

    @Step("Make post new booking")
    public BookingSteps makePostNewBookingCall(Booking booking) {
        ValidatableResponse resp = ra.body(booking).post("/").then();
        return this;
    }

    @Step("Make get all bookings")
    public BookingSteps makeGetAllBookingsCall(Integer roomId) {
        if (roomId != null) {
            ra = ra.queryParam("roomid", roomId);
        }

        ra.body("").get("/").then();
        return this;
    }

    @Step("Make get booking call")
    public BookingSteps makeGetBookingCall(Integer bookingId) {
        ra.body("").get("/" + bookingId).then();
        return this;
    }

    @Step("Make delete booking call")
    public BookingSteps makeDeleteBookingCall(Integer bookingId) {
        ra.body("").delete("/" + bookingId).then();
        return this;
    }

    @Step("Extract created booking from response")
    public Booking extractCreatedBooking() {
        return then().extract().as(CreatedBooking.class).getBooking();
    }

    @Step("Extract booking from response")
    public Booking extractBooking() {
        return then().extract().as(Booking.class);
    }

    @Step("Extract booking from response")
    public Booking[] extractBookings() {
        return then().extract().jsonPath().getObject("bookings", Booking[].class);
    }

    @Step("Assert booking are equal")
    public void checkBookingsAreEqual(Booking expected, Booking actual) {
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(actual.getBookingid()).as("Bookingid").isNotEqualTo(0);
        softly.assertThat(actual.getRoomid()).as("Roomid").isEqualTo(expected.getRoomid());
        softly.assertThat(actual.getLastname()).as("Lastname").isEqualTo(expected.getLastname());
        softly.assertThat(actual.getFirstname()).as("Firstname").isEqualTo(expected.getFirstname());
        softly.assertThat(actual.getDepositpaid()).as("Depositpaid").isEqualTo(expected.getDepositpaid());
        softly.assertThat(actual.getBookingdates().getCheckin()).as("Checkin").isEqualTo(expected.getBookingdates().getCheckin());
        softly.assertThat(actual.getBookingdates().getCheckout()).as("Checkout").isEqualTo(expected.getBookingdates().getCheckout());
        softly.assertAll();
    }
}
