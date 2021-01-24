package com.seleniumeasy.api.testscripts;

import java.io.IOException;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.impl.Log4JLogger;
import org.apache.http.HttpHeaders;
import org.apache.http.entity.ContentType;
import org.testng.annotations.BeforeClass;

import com.google.gson.Gson;
import com.seleniumeasy.api.testobjects.BookingDateVo;
import com.seleniumeasy.api.testobjects.BookingVo;
import com.seleniumeasy.framework.config.Property;

public class ApiBaseBooking {
	
	protected static String createdBookingId = "";
	
	protected static String createdBookingIdforNegative = "";
	
	protected static final Gson gson = new Gson();
	
	protected static BookingDateVo bookingdates;
	
	protected static BookingVo bookingInput, bookingInputUpdate;
	
	protected static final Log logger = new Log4JLogger(ApiBaseBooking.class.getName());
	
	protected String clientUrl = "";
	
	protected Map<String, String> headerMap = new HashMap<>();
	
	@BeforeClass
	protected void init() throws IOException, ParseException {
		logger.info("*****INSIDE SETUP****");
		clientUrl = Property.getProperty("api.booker.url");
		createdBookingIdforNegative = "991";
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		bookingdates = new BookingDateVo(formatter.parse("2021-01-01"), formatter.parse("2021-01-01"));
		bookingInput = new BookingVo("Rakesh", "Elangovan", new BigInteger("1010"), true, bookingdates,
				"Snacks");
		
		bookingInputUpdate = new BookingVo("Rakesh", "Elangovan", new BigInteger("1010"), true, bookingdates,
				"Dinner");
		headerMap.put(HttpHeaders.CONTENT_TYPE, ContentType.APPLICATION_JSON.toString());
		headerMap.put(HttpHeaders.ACCEPT, "application/json");
		logger.info("*****DONE SETUP****");
	}


}
