package com.seleniumeasy.api.testobjects;

import java.io.Serializable;

public class BookingConfirmationVo implements Serializable{
	
	private static final long serialVersionUID = -4339221151070760432L;
	
	public BookingConfirmationVo() {
		super();
	}

	public BookingConfirmationVo(String bookingid, BookingVo booking) {
		super();
		this.bookingid = bookingid;
		this.booking = booking;
	}

	private String bookingid;
	
	private BookingVo booking;

	public String getBookingid() {
		return bookingid;
	}

	public void setBookingid(String bookingid) {
		this.bookingid = bookingid;
	}

	public BookingVo getBooking() {
		return booking;
	}

	public void setBooking(BookingVo booking) {
		this.booking = booking;
	}

	@Override
	public String toString() {
		return "BookingConfirmation [bookingid=" + bookingid + ", booking=" + booking + "]";
	}
	
}
