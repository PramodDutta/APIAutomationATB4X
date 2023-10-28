package com.thetestingacademy.payloads.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.thetestingacademy.payloads.request.Booking;


public class BookingRespons {

    @SerializedName("bookingid")
    @Expose
    private Integer bookingid;
    @SerializedName("booking")
    @Expose
    private Booking booking;

    public Integer getBookingid() {
        return bookingid;
    }

    public void setBookingid(Integer bookingid) {
        this.bookingid = bookingid;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

}