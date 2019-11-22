package models;

import models.booking.Booking;
import models.booking.BookingDates;
import net.andreinc.mockneat.MockNeat;

import java.time.LocalDate;

import static net.andreinc.mockneat.types.enums.StringType.NUMBERS;

public class CreateItemsHelper {
    public static Booking createNewBooking(int roomId, LocalDate startDate, LocalDate endDate) {
        MockNeat mock = MockNeat.threadLocal();
        Booking newBooking = new Booking();
        newBooking.setRoomid(roomId);

        BookingDates bookingDates = new BookingDates();
        bookingDates.setCheckin(startDate);
        bookingDates.setCheckout(endDate);

        newBooking.setBookingdates(bookingDates);
        newBooking.setDepositpaid(true);
        newBooking.setFirstname(mock.names().val());
        newBooking.setLastname(mock.names().val());
        newBooking.setEmail(mock.emails().val());
        newBooking.setPhone(mock.strings().size(15).type(NUMBERS).val());

        return newBooking;
    }
}
