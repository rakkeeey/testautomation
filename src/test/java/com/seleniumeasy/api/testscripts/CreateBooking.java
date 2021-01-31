package com.seleniumeasy.api.testscripts;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.entity.StringEntity;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.seleniumeasy.api.testobjects.BookingConfirmationVo;
import com.seleniumeasy.framework.api.BookerApiClientUtil;
import com.seleniumeasy.framework.reporting.ReportUtility;

/**
 * This is a test case to Test positive & negative
 * test cases of Create Booking
 * 
 * @author Rakesh
 *
 */
public class CreateBooking extends ApiBaseBooking{

	/**
	 * This method test for Positive Booking Creation
	 * 
	 * @return Nothing
	 * @throws IOException
	 */
	@Test(priority=0)
	public void createBookingTest() throws IOException {
		// File requestFile = new File(System.getProperty("user.dir")+"\\Requests\\CreateRequestP.json");
		
		logger.info("*****INSIDE CREATE*****");
		ReportUtility.setReportingData("createBookingTest", "API_TC_01_CreateBookingPositiveFlow", "Verify if the booking is done",
				"Verify the booking", "Successful booking ID creation", 
				"Booking id successfully created", "Failed to verify the booking");
		
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
		ReportUtility.reportingResults("Pass", "Creation of Booking ID",
				"Successfully created booking ID is "+createdBookingId
				+" Booking Details : "+entity.getBooking().toString(),
				"User should be able to create a booking ID");
	
	}
	
	/**
	 * This method test for Negative Booking Creation Flow
	 * 
	 * @return Nothing
	 * @throws IOException
	 */
	@Test(priority=1)
	public void createBookingTestNegative() throws IOException {		
		// File requestFile = new File(System.getProperty("user.dir")+"\\Requests\\CreateRequestP.json");
		logger.info("*****INSIDE CREATE*****");
		ReportUtility.setReportingData("createBookingTestNegative", "API_TC_02_CreateBookingNegativeFlow", 
				"Verify if the booking is not done",
				"Verify the booking is not done", "Failed Booking", 
				"Booking id not created", "Failed to verify the status");
		
		bookingInput.setLastname("E");

		HttpResponse response = BookerApiClientUtil.fetchPostResponse(clientUrl, 
				new StringEntity(gson.toJson(bookingInput)), false, new HashMap<String, String>());

		logger.debug("Created Response Status==>"+response.getStatusLine().getStatusCode());
		Assert.assertEquals(response.getStatusLine().getStatusCode(), HttpStatus.SC_INTERNAL_SERVER_ERROR);
				
		logger.info("*****END OF CREATE*****");
		ReportUtility.reportingResults("Pass", "Booking ID not created",
				"Received failure response code is "+response.getStatusLine().getStatusCode(),
				"User should not be able to submit a booking without proper headers");
	}	
	
}
