package com.seleniumeasy.api.testobjects;

import java.io.Serializable;
import java.math.BigInteger;

public class BookingVo implements Serializable{	

	private static final long serialVersionUID = 1L;

	public BookingVo(){
		super();
	}

	public BookingVo(String firstname, String lastname, BigInteger totalprice, boolean depositpaid,
			BookingDateVo bookingdates, String additionalneeds) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.totalprice = totalprice;
		this.depositpaid = depositpaid;
		this.bookingdates = bookingdates;
		this.additionalneeds = additionalneeds;
	}

	private String firstname;
	
	private String lastname;
	
	private BigInteger totalprice;
	
	private boolean depositpaid;
	
	private BookingDateVo bookingdates;
	
	private String additionalneeds;	

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public BigInteger getTotalprice() {
		return totalprice;
	}

	public void setTotalprice(BigInteger totalprice) {
		this.totalprice = totalprice;
	}

	public boolean isDepositpaid() {
		return depositpaid;
	}

	public void setDepositpaid(boolean depositpaid) {
		this.depositpaid = depositpaid;
	}

	public BookingDateVo getBookingdates() {
		return bookingdates;
	}

	public void setBookingdates(BookingDateVo bookingdates) {
		this.bookingdates = bookingdates;
	}

	public String getAdditionalneeds() {
		return additionalneeds;
	}

	public void setAdditionalneeds(String additionalneeds) {
		this.additionalneeds = additionalneeds;
	}

	@Override
	public String toString() {
		return "firstname=" + firstname + ", lastname=" + lastname + ", totalprice=" + totalprice
				+ ", depositpaid=" + depositpaid + ", bookingdates=" + bookingdates + ", additionalneeds="
				+ additionalneeds;
	}
	
}
