package com.example.hotel_booking.util;

import com.example.hotel_booking.model.Booking;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static com.example.hotel_booking.model.AbstractBaseEntity.START_SEQ;
import static org.assertj.core.api.Assertions.assertThat;

public class BookingTestData {
    public static final int BOOKING_ID = START_SEQ + 12;
    public static final int BOOKING2_ID = BOOKING_ID + 1;
    public static final int BOOKING3_ID = BOOKING_ID + 2;
    public static final int BOOKING4_ID = BOOKING_ID + 3;
    public static final int BOOKING5_ID = BOOKING_ID + 4;
    public static final int BOOKING6_ID = BOOKING_ID + 5;

    public static final Booking BOOKING = new Booking(BOOKING_ID,
            LocalDate.of(2018, 11, 7), LocalDate.of(2018, 11, 8));
    public static final Booking BOOKING_2 = new Booking(BOOKING2_ID,
            LocalDate.of(2018, 11, 9), LocalDate.of(2018, 11, 11));
    public static final Booking BOOKING_3 = new Booking(BOOKING3_ID,
            LocalDate.of(2018, 11, 7), LocalDate.of(2018, 11, 8));
    public static final Booking BOOKING_4 = new Booking(BOOKING4_ID,
            LocalDate.of(2018, 11, 8), LocalDate.of(2018, 11, 10));
    public static final Booking BOOKING_5 = new Booking(BOOKING5_ID,
            LocalDate.of(2018, 11, 11), LocalDate.of(2018, 11, 12));
    public static final Booking BOOKING_6 = new Booking(BOOKING6_ID,
            LocalDate.of(2018, 11, 14), LocalDate.of(2018, 11, 15));

    public static final List<Booking> LIST_BOOKINGS = Arrays.asList(BOOKING, BOOKING_2, BOOKING_3, BOOKING_4, BOOKING_5, BOOKING_6);
    public static final List<Booking> BOOKING_LIST_BY_USER_1 = Arrays.asList(BOOKING, BOOKING_5, BOOKING_6);

    public static void assertMatch(Booking actual, Booking expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "user", "room");
    }

    public static void assertMatch(Iterable<Booking> actual, Booking... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Booking> actual, Iterable<Booking> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("user", "room").isEqualTo(expected);
    }
}
