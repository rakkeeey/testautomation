package com.seleniumeasy.api.testscripts;



import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.entity.StringEntity;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.seleniumeasy.api.testobjects.BookingConfirmationVo;
import com.seleniumeasy.framework.api.BookerApiClientUtil;
import com.seleniumeasy.framework.reporting.Global;
import com.seleniumeasy.framework.reporting.Utility;


public class CreateBooking extends ApiBaseBooking{

	
	@Test(priority=0)
	public void createBookingTest() throws IOException, ParseException {
	
		Global.sScriptName = "createBookingTest";
		Global.useCaseName = "API_TC_01_CreateBookingPositiveFlow";
		Global.useCaseDescription = "Verify if the booking is done";
		Global.curHighLight = true;
		Global.curHeading = "Verify the booking";
		Global.ER = "Successful booking ID creation";
		Global.EAR = "Booking id successfully created";
		Global.UEAR = "Failed to verify the booking";

		Utility.reportingResults("Pass", Global.curHeading, Global.EAR, Global.ER);
		Global.curHighLight = false;
		Global.prevHeading = Global.curHeading;
		Global.PER = Global.ER;
		Global.PEAR = Global.EAR;
		Global.PUEAR = Global.UEAR;
		Global.curBC = "";
		// File requestFile = new File(System.getProperty("user.dir")+"\\Requests\\CreateRequestP.json");
		
		logger.info("*****INSIDE CREATE*****");

		HttpResponse response = BookerApiClientUtil.fetchPostResponse(clientUrl, 
				new StringEntity(gson.toJson(bookingInput)), false, headerMap);

		logger.debug("Created Response Status==>"+response.getStatusLine().getStatusCode());
		Assert.assertEquals(response.getStatusLine().getStatusCode(), HttpStatus.SC_OK);
				
		String responseMessage = BookerApiClientUtil.getResponseMessage(response.getEntity());		

		BookingConfirmationVo entity = gson.fromJson(responseMessage, BookingConfirmationVo.class);

		logger.debug("Created Response==>"+entity.toString());
		BookerApiClientUtil.writeResponseMessage(responseMessage, "1CreateResponseP.json");
		
		Assert.assertEquals(entity.getBooking().getFirstname(), bookingInput.getFirstname());
		createdBookingId = entity.getBookingid();
		logger.info("*****END OF CREATE*****");
		Utility.reportingResults("Pass", "Creation of Booking ID",
				"Successfully created booking ID is "+createdBookingId
				+" Booking Details : "+entity.getBooking().toString(),
				"User should be able to create a booking ID");
	
	}
	
	@Test(priority=1)
	public void createBookingTestNegative() throws IOException, ParseException {
		Global.sScriptName = "createBookingTestNegative";
		Global.useCaseName = "API_TC_02_CreateBookingNegativeFlow";
		Global.useCaseDescription = "Verify if the booking is not done";
		Global.curHighLight = true;
		Global.curHeading = "Verify the booking is not done";
		Global.ER = "Failed Booking";
		Global.EAR = "Booking id not created";
		Global.UEAR = "Failed to verify the status";

		Utility.reportingResults("Pass", Global.curHeading, Global.EAR, Global.ER);
		Global.curHighLight = false;
		Global.prevHeading = Global.curHeading;
		Global.PER = Global.ER;
		Global.PEAR = Global.EAR;
		Global.PUEAR = Global.UEAR;
		Global.curBC = "";
		// File requestFile = new File(System.getProperty("user.dir")+"\\Requests\\CreateRequestP.json");
		
		logger.info("*****INSIDE CREATE*****");
		String toTest = bookingInput.getLastname();
		bookingInput.setLastname("E");

		HttpResponse response = BookerApiClientUtil.fetchPostResponse(clientUrl, 
				new StringEntity(gson.toJson(bookingInput)), false, new HashMap<String, String>());

		logger.debug("Created Response Status==>"+response.getStatusLine().getStatusCode());
		Assert.assertEquals(response.getStatusLine().getStatusCode(), HttpStatus.SC_INTERNAL_SERVER_ERROR);
				
		logger.info("*****END OF CREATE*****");
		Utility.reportingResults("Pass", "Booking ID not created",
				"Received failure response code is "+response.getStatusLine().getStatusCode(),
				"User should not be able to submit a booking without proper headers");
	}	
	
}
