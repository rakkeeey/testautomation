package com.seleniumeasy.api.testscripts;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.seleniumeasy.api.testobjects.BookingVo;
import com.seleniumeasy.framework.api.BookerApiClientUtil;
import com.seleniumeasy.framework.reporting.ReportUtility;

/**
 * This is a test case to Test positive & negative
 * test cases of Get Booking
 * 
 * @author Rakesh
 *
 */
public class GetBooking extends ApiBaseBooking{
	
	/**
	 * This method test for Positive Booking Retrieval Flow
	 * 
	 * @throws IOException
	 */
	@Test(priority=2)
	public void getBookingTest() throws IOException {
	  	//File requestFile = new File(System.getProperty("user.dir")+"\\Requests\\GetRequestP.json");
		
    	logger.info("*****INSIDE GET*****");
    	
    	ReportUtility.setReportingData("getBookingTest", "API_TC_03_GetBookingPositiveFlow", 
				"Verify if the booking is fetched",
				"Booking fetched Successfully", "Successful booking Details fetched", 
				"Booking id successfully fetched", "Failed to fetch the booking");
    	
    	HttpResponse response = BookerApiClientUtil.fetchGetResponse(clientUrl+createdBookingId, false, headerMap);

    	logger.debug("Get Response Status==>"+response.getStatusLine().getStatusCode());
    	Assert.assertEquals(response.getStatusLine().getStatusCode(), HttpStatus.SC_OK);
    	
    	String responseMessage = BookerApiClientUtil.getResponseMessage(response.getEntity());
    	
    	BookingVo entity = gson.fromJson(responseMessage, BookingVo.class);
    	logger.debug("Get Response==>"+entity.toString());
    	BookerApiClientUtil.writeResponseMessage(responseMessage, "2GetResponseP.json");
    	
    	Assert.assertEquals(entity.getAdditionalneeds(), bookingInput.getAdditionalneeds());
    	ReportUtility.reportingResults("Pass", "Fetching Booking Details",
				"Successfully fetched booking is for User "+entity.getFirstname()+" "+entity.getLastname()
				+" Booking Details : "+entity.toString(),
				"User should be able to get Booking details");
    	logger.info("*****END OF GET*****");		
	}
	
	/**
	 * This method test for Negative Booking Retrieval Flow
	 * 
	 * @throws IOException
	 */
	@Test(priority=3)
	public void getBookingTestNegative() throws IOException {
	  	//File requestFile = new File(System.getProperty("user.dir")+"\\Requests\\GetRequestP.json");
		
    	logger.info("*****INSIDE GET*****");
    	
    	ReportUtility.setReportingData("getBookingTestNegative", "API_TC_04_GetBookingNegativeFlow", 
				"Verify if the booking is not fetched",
				"Verify the booking is not fetched", "Retrieving Booking details failed", 
				"Booking details not retrieved", "Failed to verify the status");
    	
    	HttpResponse response = BookerApiClientUtil.fetchGetResponse(clientUrl+createdBookingIdforNegative, false, headerMap);

    	logger.debug("Get Response Status==>"+response.getStatusLine().getStatusCode());
    	Assert.assertEquals(response.getStatusLine().getStatusCode(), HttpStatus.SC_NOT_FOUND);
    	ReportUtility.reportingResults("Pass", "Booking not fetched",
				"Received failure response code is "+response.getStatusLine().getStatusCode(),
				"User should not be able to retrieve Booking details for invalid Id");
    	logger.info("*****END OF GET*****");		
	}

}
