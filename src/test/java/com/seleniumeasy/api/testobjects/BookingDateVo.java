package com.seleniumeasy.api.testobjects;

import java.io.Serializable;
import java.util.Date;

public class BookingDateVo implements Serializable{

	private static final long serialVersionUID = 1L;

	public BookingDateVo() {
		super();
	}

	public BookingDateVo(Date checkin, Date checkout) {
		super();
		this.checkin = checkin;
		this.checkout = checkout;
	}

	private Date checkin;
	
	private Date checkout;

	public Date getCheckin() {
		return checkin;
	}

	public void setCheckin(Date checkin) {
		this.checkin = checkin;
	}

	public Date getCheckout() {
		return checkout;
	}

	public void setCheckout(Date checkout) {
		this.checkout = checkout;
	}

	@Override
	public String toString() {
		return "BookingDate [checkin=" + checkin + ", checkout=" + checkout + "]";
	}
	
}
